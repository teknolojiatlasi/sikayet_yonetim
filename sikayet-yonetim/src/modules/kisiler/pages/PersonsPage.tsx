import { useState, useEffect } from 'react'
import { getPersons, createPerson, type Person } from '../../../api/kisiApi'

export function PersonsPage() {
  const [persons, setPersons] = useState<Person[]>([])
  const [showCreateModal, setShowCreateModal] = useState(false)
  const [formData, setFormData] = useState<Person>({
    ad: '',
    soyad: '',
    telefon: '',
    email: '',
  })
  const [isLoading, setIsLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    fetchPersons()
  }, [])

  const fetchPersons = async () => {
    try {
      const data = await getPersons()
      setPersons(data || [])
    } catch (err) {
      setError('Kisiler yüklenemedi')
    }
  }

  const handleCreatePerson = async (e: React.FormEvent) => {
    e.preventDefault()
    setIsLoading(true)
    try {
      await createPerson(formData)
      setShowCreateModal(false)
      setFormData({ ad: '', soyad: '', telefon: '', email: '' })
      await fetchPersons()
    } catch (err) {
      setError('Kisi oluşturulamadı')
    } finally {
      setIsLoading(false)
    }
  }

  return (
    <>
      <section className="page-header">
        <div>
          <div className="eyebrow">Kisiler</div>
          <h2 className="page-title">Kisi Kayitlari</h2>
        </div>
        <button className="button-primary" type="button" onClick={() => setShowCreateModal(true)}>
          Yeni kisi
        </button>
      </section>

      {error && <div className="error-text" style={{ padding: '16px' }}>{error}</div>}

      <section className="table-card">
        <table>
          <thead>
            <tr>
              <th>Ad Soyad</th>
              <th>Telefon</th>
              <th>E-posta</th>
            </tr>
          </thead>
          <tbody>
            {persons.length > 0 ? (
              persons.map((person) => (
                <tr key={person.id}>
                  <td>
                    {person.ad} {person.soyad}
                  </td>
                  <td>{person.telefon}</td>
                  <td>{person.email}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan={3} style={{ textAlign: 'center' }}>
                  Kisi bulunamadi
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </section>

      {showCreateModal && (
        <div style={modalOverlayStyle}>
          <div style={modalStyle}>
            <h2>Yeni Kisi Ekle</h2>
            <form onSubmit={handleCreatePerson}>
              <label className="field-group">
                <span>Ad</span>
                <input
                  type="text"
                  value={formData.ad}
                  onChange={(e) => setFormData({ ...formData, ad: e.target.value })}
                  required
                />
              </label>
              <label className="field-group">
                <span>Soyad</span>
                <input
                  type="text"
                  value={formData.soyad}
                  onChange={(e) => setFormData({ ...formData, soyad: e.target.value })}
                  required
                />
              </label>
              <label className="field-group">
                <span>Telefon</span>
                <input
                  type="tel"
                  value={formData.telefon}
                  onChange={(e) => setFormData({ ...formData, telefon: e.target.value })}
                  required
                />
              </label>
              <label className="field-group">
                <span>E-posta</span>
                <input
                  type="email"
                  value={formData.email}
                  onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                  required
                />
              </label>
              <div style={{ display: 'flex', gap: '8px', marginTop: '16px' }}>
                <button type="submit" className="button-primary" disabled={isLoading}>
                  {isLoading ? 'Ekleniyor...' : 'Ekle'}
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
