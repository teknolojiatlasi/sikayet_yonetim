const rows = [
  ['SY-2026-1042', 'Gec teslim edilen kargo', 'Ayse Demir', 'Elif Acar', 'Incelemede', 'Yuksek'],
  ['SY-2026-1041', 'Eksik fatura bilgisi', 'Mert Yalcin', 'Can Tekin', 'Atandi', 'Orta'],
  ['SY-2026-1038', 'Personel davranis bildirimi', 'Selin Oz', 'Gul Kose', 'Sonuclandi', 'Dusuk'],
]

export function ComplaintsPage() {
  return (
    <>
      <section className="page-header">
        <div>
          <div className="eyebrow">Sikayetler</div>
          <h2 className="page-title">Liste ve Filtreleme</h2>
        </div>
        <button className="button-primary" type="button">Kayit ac</button>
      </section>

      <section className="panel">
        <div className="filters">
          <label className="field"><span>Sikayet No</span><input placeholder="SY-2026-..." /></label>
          <label className="field"><span>Durum</span><select><option>Tumu</option><option>Yeni</option><option>Atandi</option><option>Incelemede</option></select></label>
          <label className="field"><span>Atanan Kullanici</span><input placeholder="Kullanici ara" /></label>
          <label className="field"><span>Oncelik</span><select><option>Tumu</option><option>Yuksek</option><option>Orta</option><option>Dusuk</option></select></label>
        </div>
      </section>

      <section className="table-card">
        <table>
          <thead>
            <tr><th>No</th><th>Konu</th><th>Kisi</th><th>Atanan</th><th>Durum</th><th>Oncelik</th></tr>
          </thead>
          <tbody>
            {rows.map((row) => (
              <tr key={row[0]}>
                <td>{row[0]}</td><td>{row[1]}</td><td>{row[2]}</td><td>{row[3]}</td>
                <td><span className={`badge ${row[4] === 'Sonuclandi' ? 'success' : row[4] === 'Incelemede' ? 'warning' : 'danger'}`}>{row[4]}</span></td>
                <td>{row[5]}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>
    </>
  )
}
