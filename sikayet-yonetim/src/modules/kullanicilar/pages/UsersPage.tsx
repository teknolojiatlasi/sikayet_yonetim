const users = [
  ['admin', 'ROLE_ADMIN', 'Aktif'],
  ['elif.acar', 'ROLE_INCELEYEN', 'Aktif'],
  ['can.tekin', 'ROLE_OPERATOR', 'Pasif'],
]

export function UsersPage() {
  return (
    <>
      <section className="page-header">
        <div>
          <div className="eyebrow">Yetkilendirme</div>
          <h2>Kullanici ve rol yonetimi</h2>
          <p>
            Rol, yetki ve aktiflik durumunun yonetilecegi CRUD kabugu bu sayfa
            ile baslatildi.
          </p>
        </div>
        <button className="button-primary" type="button">
          Kullanici ekle
        </button>
      </section>

      <section className="table-card">
        <table>
          <thead>
            <tr>
              <th>Kullanici adi</th>
              <th>Rol</th>
              <th>Durum</th>
            </tr>
          </thead>
          <tbody>
            {users.map(([username, role, status]) => (
              <tr key={username}>
                <td>{username}</td>
                <td>{role}</td>
                <td>
                  <span className={`badge ${status === 'Aktif' ? 'success' : 'danger'}`}>
                    {status}
                  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>
    </>
  )
}
