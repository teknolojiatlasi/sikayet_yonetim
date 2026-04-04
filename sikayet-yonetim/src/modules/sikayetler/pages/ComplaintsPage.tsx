import { Link } from 'react-router-dom'

const complaints = [
  {
    id: 1,
    no: 'SY-2026-1042',
    subject: 'Gec teslim edilen kargo',
    person: 'Ayse Demir',
    assignee: 'Elif Acar',
    status: 'Incelemede',
    priority: 'Yuksek',
  },
  {
    id: 2,
    no: 'SY-2026-1041',
    subject: 'Eksik fatura bilgisi',
    person: 'Mert Yalcin',
    assignee: 'Can Tekin',
    status: 'Atandi',
    priority: 'Orta',
  },
  {
    id: 3,
    no: 'SY-2026-1038',
    subject: 'Personel davranis bildirimi',
    person: 'Selin Oz',
    assignee: 'Gul Kose',
    status: 'Sonuclandi',
    priority: 'Dusuk',
  },
]

export function ComplaintsPage() {
  return (
    <>
      <section className="page-header">
        <div>
          <div className="eyebrow">Sikayet modulu</div>
          <h2>Liste, filtre ve aksiyon merkezi</h2>
          <p>
            Bu ekran listeleme, filtreleme, durum izleme ve detay ekranina gecis
            icin taban kabuk olarak hazirlandi.
          </p>
        </div>
        <button className="button-primary" type="button">
          Kayit olustur
        </button>
      </section>

      <section className="panel">
        <div className="filters">
          <label className="field">
            <span>Sikayet no</span>
            <input placeholder="SY-2026-..." />
          </label>
          <label className="field">
            <span>Durum</span>
            <select defaultValue="all">
              <option value="all">Tum durumlar</option>
              <option value="new">Yeni</option>
              <option value="assigned">Atandi</option>
              <option value="review">Incelemede</option>
            </select>
          </label>
          <label className="field">
            <span>Atanan kullanici</span>
            <input placeholder="Kullanici ara" />
          </label>
          <label className="field">
            <span>Tarih araligi</span>
            <input placeholder="01.04.2026 - 04.04.2026" />
          </label>
        </div>
      </section>

      <section className="table-card">
        <div className="table-header">
          <h3 className="section-title">Aktif kayitlar</h3>
        </div>
        <table>
          <thead>
            <tr>
              <th>No</th>
              <th>Konu</th>
              <th>Kisi</th>
              <th>Atanan</th>
              <th>Durum</th>
              <th>Oncelik</th>
              <th>Aksiyon</th>
            </tr>
          </thead>
          <tbody>
            {complaints.map((complaint) => (
              <tr key={complaint.id}>
                <td>{complaint.no}</td>
                <td>{complaint.subject}</td>
                <td>{complaint.person}</td>
                <td>{complaint.assignee}</td>
                <td>
                  <span
                    className={`badge ${
                      complaint.status === 'Sonuclandi'
                        ? 'success'
                        : complaint.status === 'Incelemede'
                          ? 'warning'
                          : 'danger'
                    }`}
                  >
                    {complaint.status}
                  </span>
                </td>
                <td>{complaint.priority}</td>
                <td>
                  <Link className="button-ghost" to={`/complaints/${complaint.id}`}>
                    Detay
                  </Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>
    </>
  )
}
