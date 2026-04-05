import { useState, useEffect } from 'react'
import { getComplaints, createComplaint, getComplaintTypes } from '../../../api/sikayetApi'

interface ComplaintData {
  id: string
  numarası: string
  tür: string
  konu: string
  kişi: string
  atanmış: string
  durum: string
  öncelik: string
}

interface CreateFormData {
  typeCode: string
  title: string
  description: string
}

export function ComplaintsPage() {
  const [complaints, setComplaints] = useState<ComplaintData[]>([])
  const [showCreateModal, setShowCreateModal] = useState(false)
  const [complaintTypes, setComplaintTypes] = useState<any[]>([])
  const [formData, setFormData] = useState<CreateFormData>({
    typeCode: '',
    title: '',
    description: '',
  })
  const [isLoading, setIsLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    fetchComplaints()
    fetchComplaintTypes()
  }, [])

  const fetchComplaints = async () => {
    try {
      const data = await getComplaints()
      setComplaints(data || [])
    } catch (err) {
      setError('Sikayetler yüklenemedi')
    }
  }

  const fetchComplaintTypes = async () => {
    try {
      const data = await getComplaintTypes()
      setComplaintTypes(data || [])
    } catch (err) {
      console.error('Sikayet türleri yüklenemedi', err)
    }
  }

  const handleCreateComplaint = async (e: React.FormEvent) => {
    e.preventDefault()
    setIsLoading(true)
    try {
      await createComplaint(formData)
      setShowCreateModal(false)
      setFormData({ typeCode: '', title: '', description: '' })
      await fetchComplaints()
    } catch (err) {
      setError('Sikayet oluşturulamadı')
    } finally {
      setIsLoading(false)
    }
  }

  return (
    <>
      <section className="page-header">
        <div>
          <div className="eyebrow">Sikayetler</div>
          <h2 className="page-title">Liste ve Filtreleme</h2>
        </div>
        <button className="button-primary" type="button" onClick={() => setShowCreateModal(true)}>
          Kayit ac
        </button>
      </section>

      <section className="panel">
        <div className="filters">
          <label className="field">
            <span>Sikayet No</span>
            <input placeholder="SY-2026-..." />
          </label>
          <label className="field">
            <span>Durum</span>
            <select>
              <option>Tumu</option>
              <option>Yeni</option>
              <option>Atandi</option>
              <option>Incelemede</option>
            </select>
          </label>
          <label className="field">
            <span>Atanan Kullanici</span>
            <input placeholder="Kullanici ara" />
          </label>
          <label className="field">
            <span>Oncelik</span>
            <select>
              <option>Tumu</option>
              <option>Yuksek</option>
              <option>Orta</option>
              <option>Dusuk</option>
            </select>
          </label>
        </div>
      </section>

      {error && <div className="error-text" style={{ padding: '16px' }}>{error}</div>}

      <section className="table-card">
        <table>
          <thead>
            <tr>
              <th>No</th>
              <th>Konu</th>
              <th>Kisi</th>
              <th>Atanan</th>
              <th>Durum</th>
              <th>Oncelik</th>
            </tr>
          </thead>
          <tbody>
            {complaints.length > 0 ? (
              complaints.map((complaint) => (
                <tr key={complaint.id}>
                  <td>{complaint.numarası}</td>
                  <td>{complaint.konu}</td>
                  <td>{complaint.kişi}</td>
                  <td>{complaint.atanmış}</td>
                  <td>
                    <span
                      className={`badge ${
                        complaint.durum === 'Sonuclandi'
                          ? 'success'
                          : complaint.durum === 'Incelemede'
                            ? 'warning'
                            : 'danger'
                      }`}
                    >
                      {complaint.durum}
                    </span>
                  </td>
                  <td>{complaint.öncelik}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan={6} style={{ textAlign: 'center' }}>
                  Sikayet bulunamadi
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </section>

      {showCreateModal && (
        <div style={modalOverlayStyle}>
          <div style={modalStyle}>
            <h2>Yeni Sikayet Olustur</h2>
            <form onSubmit={handleCreateComplaint}>
              <label className="field-group">
                <span>Sikayet Türü</span>
                <select
                  value={formData.typeCode}
                  onChange={(e) => setFormData({ ...formData, typeCode: e.target.value })}
                  required
                >
                  <option value="">Secin</option>
                  {complaintTypes.map((type) => (
                    <option key={type.kod} value={type.kod}>
                      {type.ad}
                    </option>
                  ))}
                </select>
              </label>
              <label className="field-group">
                <span>Baslik</span>
                <input
                  type="text"
                  value={formData.title}
                  onChange={(e) => setFormData({ ...formData, title: e.target.value })}
                  required
                />
              </label>
              <label className="field-group">
                <span>Aciklama</span>
                <textarea
                  value={formData.description}
                  onChange={(e) => setFormData({ ...formData, description: e.target.value })}
                  required
                  rows={4}
                />
              </label>
              <div style={{ display: 'flex', gap: '8px', marginTop: '16px' }}>
                <button type="submit" className="button-primary" disabled={isLoading}>
                  {isLoading ? 'Olusturuluyor...' : 'Olustur'}
                </button>
                <button
                  type="button"
                  className="button-secondary"
                  onClick={() => setShowCreateModal(false)}
                  disabled={isLoading}
                >
                  Iptal
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </>
  )
}

const modalOverlayStyle: React.CSSProperties = {
  position: 'fixed',
  top: 0,
  left: 0,
  right: 0,
  bottom: 0,
  backgroundColor: 'rgba(0, 0, 0, 0.5)',
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
  zIndex: 1000,
}

const modalStyle: React.CSSProperties = {
  backgroundColor: 'white',
  padding: '24px',
  borderRadius: '8px',
  minWidth: '400px',
  maxWidth: '600px',
  boxShadow: '0 4px 16px rgba(0, 0, 0, 0.1)',
}
