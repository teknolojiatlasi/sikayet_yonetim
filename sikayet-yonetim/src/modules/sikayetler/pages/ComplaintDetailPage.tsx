import { useState, useEffect } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import {
  getComplaint,
  assignComplaint,
  changeComplaintStatus,
  createComplaintResult,
  getComplaintStatuses,
} from '../../../api/sikayetApi'

interface TabType {
  details: string
  files: string
  logs: string
  history: string
}

const tabs: TabType = {
  details: 'details',
  files: 'files',
  logs: 'logs',
  history: 'history',
}

export function ComplaintDetailPage() {
  const { id } = useParams<{ id: string }>()
  const navigate = useNavigate()
  const [complaint, setComplaint] = useState<any>(null)
  const [activeTab, setActiveTab] = useState('details')
  const [statuses, setStatuses] = useState<any[]>([])
  const [isLoading, setIsLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  // Form states
  const [assignUserId, setAssignUserId] = useState('')
  const [newStatus, setNewStatus] = useState('')
  const [resultText, setResultText] = useState('')
  const [isSubmitting, setIsSubmitting] = useState(false)

  useEffect(() => {
    fetchComplaint()
    fetchStatuses()
  }, [id])

  const fetchComplaint = async () => {
    if (!id) return
    try {
      const data = await getComplaint(id)
      setComplaint(data)
    } catch (err) {
      setError('Sikayet yüklenemedi')
    } finally {
      setIsLoading(false)
    }
  }

  const fetchStatuses = async () => {
    try {
      const data = await getComplaintStatuses()
      setStatuses(data || [])
    } catch (err) {
      console.error('Durumlar yüklenemedi', err)
    }
  }

  const handleAssign = async () => {
    if (!assignUserId || !id) return
    setIsSubmitting(true)
    try {
      await assignComplaint(id, assignUserId)
      setAssignUserId('')
      await fetchComplaint()
    } catch (err) {
      setError('Atama isleminde hata')
    } finally {
      setIsSubmitting(false)
    }
  }

  const handleStatusChange = async () => {
    if (!newStatus || !id) return
    setIsSubmitting(true)
    try {
      await changeComplaintStatus(id, newStatus)
      setNewStatus('')
      await fetchComplaint()
    } catch (err) {
      setError('Durum degistirme isleminde hata')
    } finally {
      setIsSubmitting(false)
    }
  }

  const handleCreateResult = async () => {
    if (!resultText || !id) return
    setIsSubmitting(true)
    try {
      await createComplaintResult(id, resultText)
      setResultText('')
      await fetchComplaint()
    } catch (err) {
      setError('Sonuc olusturmada hata')
    } finally {
      setIsSubmitting(false)
    }
  }

  if (isLoading) return <div style={{ padding: '32px' }}>Yukleniyor...</div>
  if (!complaint) return <div style={{ padding: '32px' }}>Sikayet bulunamadi</div>

  return (
    <>
      <section className="page-header">
        <div>
          <div className="eyebrow">Sikayet Detayi</div>
          <h2 className="page-title">{complaint.numarası}</h2>
        </div>
        <button
          className="button-secondary"
          type="button"
          onClick={() => navigate('/complaints')}
        >
          Geri don
        </button>
      </section>

      {error && <div className="error-text" style={{ padding: '16px', margin: '16px' }}>{error}</div>}

      <section className="panel">
        <div style={{ display: 'flex', gap: '8px', borderBottom: '1px solid #e0e0e0', marginBottom: '16px' }}>
          {Object.entries(tabs).map(([key, value]) => (
            <button
              key={key}
              onClick={() => setActiveTab(value)}
              style={{
                padding: '12px 16px',
                background: activeTab === value ? '#f0f0f0' : 'transparent',
                border: activeTab === value ? '2px solid #2563eb' : 'none',
                borderBottom: activeTab === value ? 'none' : '1px solid #e0e0e0',
                cursor: 'pointer',
                fontSize: '14px',
                fontWeight: activeTab === value ? '600' : '400',
              }}
            >
              {key.charAt(0).toUpperCase() + key.slice(1)}
            </button>
          ))}
        </div>

        {/* Details Tab */}
        {activeTab === tabs.details && (
          <div>
            <div style={{ marginBottom: '24px' }}>
              <h3>Temel Bilgiler</h3>
              <div style={{ display: 'grid', gap: '16px', marginTop: '16px' }}>
                <div>
                  <span style={{ color: '#666', fontSize: '14px' }}>Numarası:</span>
                  <div style={{ fontSize: '16px', fontWeight: '500' }}>{complaint.numarası}</div>
                </div>
                <div>
                  <span style={{ color: '#666', fontSize: '14px' }}>Turu:</span>
                  <div style={{ fontSize: '16px', fontWeight: '500' }}>{complaint.tür}</div>
                </div>
                <div>
                  <span style={{ color: '#666', fontSize: '14px' }}>Baslik:</span>
                  <div style={{ fontSize: '16px', fontWeight: '500' }}>{complaint.başlık}</div>
                </div>
                <div>
                  <span style={{ color: '#666', fontSize: '14px' }}>Aciklama:</span>
                  <div style={{ fontSize: '16px', whiteSpace: 'pre-wrap' }}>{complaint.açıklama}</div>
                </div>
                <div>
                  <span style={{ color: '#666', fontSize: '14px' }}>Durum:</span>
                  <div style={{ fontSize: '16px', fontWeight: '500' }}>
                    <span className={`badge ${complaint.durum === 'Sonuclandi' ? 'success' : complaint.durum === 'Incelemede' ? 'warning' : 'danger'}`}>
                      {complaint.durum}
                    </span>
                  </div>
                </div>
                <div>
                  <span style={{ color: '#666', fontSize: '14px' }}>Atanmis Kisi:</span>
                  <div style={{ fontSize: '16px', fontWeight: '500' }}>{complaint.atanmışKişi || 'Henuz atanmadi'}</div>
                </div>
                <div>
                  <span style={{ color: '#666', fontSize: '14px' }}>Olusturma Tarihi:</span>
                  <div style={{ fontSize: '16px', fontWeight: '500' }}>{complaint.oluşturmaTarihi}</div>
                </div>
              </div>
            </div>

            {/* Actions */}
            <div style={{ display: 'grid', gap: '16px', borderTop: '1px solid #e0e0e0', paddingTop: '24px' }}>
              <div>
                <h4>Atama Yap</h4>
                <div style={{ display: 'flex', gap: '8px', marginTop: '8px' }}>
                  <input
                    type="text"
                    placeholder="Kullanici ID"
                    value={assignUserId}
                    onChange={(e) => setAssignUserId(e.target.value)}
                    style={{ flex: 1, padding: '8px 12px', border: '1px solid #ddd', borderRadius: '4px' }}
                  />
                  <button
                    className="button-primary"
                    onClick={handleAssign}
                    disabled={isSubmitting}
                  >
                    Ata
                  </button>
                </div>
              </div>

              <div>
                <h4>Durumu Degistir</h4>
                <div style={{ display: 'flex', gap: '8px', marginTop: '8px' }}>
                  <select
                    value={newStatus}
                    onChange={(e) => setNewStatus(e.target.value)}
                    style={{ flex: 1, padding: '8px 12px', border: '1px solid #ddd', borderRadius: '4px' }}
                  >
                    <option value="">Secin</option>
                    {statuses.map((status) => (
                      <option key={status.kod} value={status.kod}>
                        {status.ad}
                      </option>
                    ))}
                  </select>
                  <button
                    className="button-primary"
                    onClick={handleStatusChange}
                    disabled={isSubmitting}
                  >
                    Degistir
                  </button>
                </div>
              </div>

              <div>
                <h4>Sonuc Olustur</h4>
                <textarea
                  placeholder="Sonuc metni giriniz..."
                  value={resultText}
                  onChange={(e) => setResultText(e.target.value)}
                  style={{
                    width: '100%',
                    minHeight: '100px',
                    padding: '12px',
                    border: '1px solid #ddd',
                    borderRadius: '4px',
                    marginTop: '8px',
                    fontFamily: 'monospace',
                  }}
                />
                <button
                  className="button-primary"
                  onClick={handleCreateResult}
                  disabled={isSubmitting || !resultText}
                  style={{ marginTop: '8px' }}
                >
                  Sonuc Olustur
                </button>
              </div>
            </div>
          </div>
        )}

        {/* Files Tab */}
        {activeTab === tabs.files && (
          <div>
            <h3>Dosyalar</h3>
            <div style={{ marginTop: '16px' }}>
              <input
                type="file"
                multiple
                style={{
                  display: 'block',
                  marginBottom: '16px',
                  padding: '12px',
                  border: '2px dashed #ddd',
                  borderRadius: '4px',
                  width: '100%',
                }}
              />
              <div style={{ padding: '16px', backgroundColor: '#f9f9f9', borderRadius: '4px' }}>
                <p style={{ color: '#666' }}>Bu sikayet icin henuz dosya yuklenmemis.</p>
              </div>
            </div>
          </div>
        )}

        {/* Logs Tab */}
        {activeTab === tabs.logs && (
          <div>
            <h3>Audit Loglari</h3>
            <div style={{ marginTop: '16px' }}>
              <div style={{ padding: '16px', backgroundColor: '#f9f9f9', borderRadius: '4px' }}>
                <p style={{ color: '#666' }}>Audit loglari yuklenmektedir...</p>
              </div>
            </div>
          </div>
        )}

        {/* History Tab */}
        {activeTab === tabs.history && (
          <div>
            <h3>Durum Degisim Gecmisi</h3>
            <div style={{ marginTop: '16px' }}>
              <div style={{ padding: '16px', backgroundColor: '#f9f9f9', borderRadius: '4px' }}>
                <p style={{ color: '#666' }}>Durum degisim gecmisi yuklenmektedir...</p>
              </div>
            </div>
          </div>
        )}
      </section>
    </>
  )
}
