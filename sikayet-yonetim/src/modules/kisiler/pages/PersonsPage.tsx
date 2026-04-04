const persons = [
  ['Ayse Demir', '0555 101 11 11', 'ayse@example.com'],
  ['Mert Yalcin', '0555 202 22 22', 'mert@example.com'],
  ['Selin Oz', '0555 303 33 33', 'selin@example.com'],
]

export function PersonsPage() {
  return (
    <>
      <section className="page-header">
        <div>
          <div className="eyebrow">Kisi yonetimi</div>
          <h2>Kisi kayitlari</h2>
          <p>Sikayet kayitlarinin baglandigi kisi envanteri burada tutulur.</p>
        </div>
        <button className="button-primary" type="button">
          Yeni kisi
        </button>
      </section>

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
            {persons.map(([name, phone, mail]) => (
              <tr key={mail}>
                <td>{name}</td>
                <td>{phone}</td>
                <td>{mail}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>
    </>
  )
}
