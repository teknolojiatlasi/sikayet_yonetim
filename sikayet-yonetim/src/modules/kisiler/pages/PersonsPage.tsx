export function PersonsPage() {
  return (
    <>
      <section className="page-header">
        <div><div className="eyebrow">Kisiler</div><h2 className="page-title">Kisi Kayitlari</h2></div>
        <button className="button-primary" type="button">Yeni kisi</button>
      </section>
      <section className="table-card">
        <table>
          <thead><tr><th>Ad Soyad</th><th>Telefon</th><th>E-posta</th></tr></thead>
          <tbody>
            <tr><td>Ayse Demir</td><td>0555 101 11 11</td><td>ayse@example.com</td></tr>
            <tr><td>Mert Yalcin</td><td>0555 202 22 22</td><td>mert@example.com</td></tr>
          </tbody>
        </table>
      </section>
    </>
  )
}
