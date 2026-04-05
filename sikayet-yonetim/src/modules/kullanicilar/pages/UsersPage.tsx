import { useState, useEffect } from 'react'
import { getUsers, createUser, type User } from '../../../api/kullaniciApi'

export function UsersPage() {
  const [users, setUsers] = useState<User[]>([])
  const [showCreateModal, setShowCreateModal] = useState(false)
  const [formData, setFormData] = useState<User>({
    kullaniciAdi: '',
    ad: '',
    soyad: '',
    sifre: '',
  })
  const [isLoading, setIsLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    fetchUsers()
  }, [])

  const fetchUsers = async () => {
    try {
      const data = await getUsers()
      setUsers(data || [])
    } catch (err) {
      setError('Kullanicilar yüklenemedi')
    }
  }

  const handleCreateUser = async (e: React.FormEvent) => {
    e.preventDefault()
    setIsLoading(true)
    try {
      await createUser(formData)
      setShowCreateModal(false)
      setFormData({ kullaniciAdi: '', ad: '', soyad: '', sifre: '' })
      await fetchUsers()
    } catch (err) {
      setError('Kullanici oluşturulamadı')
    } finally {
      setIsLoading(false)
    }
  }

  return (
    <>
      <section className="page-header">
        <div>
          <div className="eyebrow">Kullanicilar</div>
          <h2 className="page-title">Rol ve Yetki Yonetimi</h2>
        </div>
        <button className="button-primary" type="button" onClick={() => setShowCreateModal(true)}>
          Kullanici ekle
        </button>
      </section>

      {error && <div className="error-text" style={{ padding: '16px' }}>{error}</div>}

      <section className="table-card">
        <table>
          <thead>
            <tr>
              <th>Kullanici</th>
              <th>Ad Soyad</th>
              <th>Durum</th>
            </tr>
          </thead>
          <tbody>
            {users.length > 0 ? (
              users.map((user) => (
                <tr key={user.id}>
                  <td>{user.kullaniciAdi}</td>
                  <td>
                    {user.ad} {user.soyad}
                  </td>
                  <td>
                    <span className={`badge ${user.aktif ? 'success' : 'danger'}`}>
                      {user.aktif ? 'Aktif' : 'Pasif'}
                    </span>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan={3} style={{ textAlign: 'center' }}>
                  Kullanici bulunamadi
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </section>

      {showCreateModal && (
        <div style={modalOverlayStyle}>
          <div style={modalStyle}>
            <h2>Yeni Kullanici Ekle</h2>
            <form onSubmit={handleCreateUser}>
              <label className="field-group">
                <span>Kullanici Adi</span>
                <input
                  type="text"
                  value={formData.kullaniciAdi}
                  onChange={(e) => setFormData({ ...formData, kullaniciAdi: e.target.value })}
                  required
                />
              </label>
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
                <span>Sifre</span>
                <input
                  type="password"
                  value={formData.sifre}
                  onChange={(e) => setFormData({ ...formData, sifre: e.target.value })}
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
