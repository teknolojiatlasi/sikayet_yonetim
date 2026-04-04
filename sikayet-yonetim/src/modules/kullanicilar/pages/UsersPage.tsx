export function UsersPage() {
  return (
    <>
      <section className="page-header">
        <div><div className="eyebrow">Kullanicilar</div><h2 className="page-title">Rol ve Yetki Yonetimi</h2></div>
        <button className="button-primary" type="button">Kullanici ekle</button>
      </section>
      <section className="table-card">
        <table>
          <thead><tr><th>Kullanici</th><th>Rol</th><th>Durum</th></tr></thead>
          <tbody>
            <tr><td>admin</td><td>ROLE_ADMIN</td><td><span className="badge success">Aktif</span></td></tr>
            <tr><td>elif.acar</td><td>ROLE_INCELEYEN</td><td><span className="badge success">Aktif</span></td></tr>
            <tr><td>can.tekin</td><td>ROLE_OPERATOR</td><td><span className="badge danger">Pasif</span></td></tr>
          </tbody>
        </table>
      </section>
    </>
  )
}
