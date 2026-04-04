import { useParams } from 'react-router-dom'

const tabs = [
  'Genel bilgi',
  'Dosyalar',
  'Atamalar',
  'Durum gecmisi',
  'Loglar',
  'Sonuc',
]

export function ComplaintDetailPage() {
  const { id } = useParams()

  return (
    <>
      <section className="page-header">
        <div>
          <div className="eyebrow">Sikayet detayi</div>
          <h2>SY-2026-10{id}</h2>
          <p>
            Sekmeli detay yapisi; dosya, atama, log ve sonuc surecini ayni
            sayfada toplamak icin hazirlandi.
          </p>
        </div>
        <div style={{ display: 'flex', gap: 12 }}>
          <button className="button-secondary" type="button">
            Atama yap
          </button>
          <button className="button-primary" type="button">
            Durum degistir
          </button>
        </div>
      </section>

      <section className="tabs">
        {tabs.map((tab, index) => (
          <div className={index === 0 ? 'tab active' : 'tab'} key={tab}>
            {tab}
          </div>
        ))}
      </section>

      <section className="detail-grid">
        <article className="detail-card">
          <div className="eyebrow">Ana bilgiler</div>
          <h3 className="section-title">Gec teslim edilen kargo</h3>
          <p>
            Musteri teslim surecinde iki gun gecikme yasandigini, surec boyunca
            saglikli bilgilendirme alamadigini beyan ediyor.
          </p>
        </article>

        <article className="detail-card">
          <div className="eyebrow">Sorumluluk</div>
          <div className="status-row">
            <span>Atanan kullanici</span>
            <strong>Elif Acar</strong>
          </div>
          <div className="status-row">
            <span>Mevcut durum</span>
            <strong>Incelemede</strong>
          </div>
          <div className="status-row">
            <span>Son guncelleme</span>
            <strong>04.04.2026 16:20</strong>
          </div>
        </article>
      </section>

      <section className="grid-2">
        <article className="panel">
          <div className="eyebrow">Durum gecmisi</div>
          <div className="timeline">
            <div className="timeline-item">Yeni kayit acildi.</div>
            <div className="timeline-item">Elif Acar kullanicisina atandi.</div>
            <div className="timeline-item">Durum Incelemede olarak guncellendi.</div>
          </div>
        </article>

        <article className="panel">
          <div className="eyebrow">Dosya ve log</div>
          <div className="timeline">
            <div className="timeline-item">teslim-kaniti.pdf yüklendi.</div>
            <div className="timeline-item">Sistem logu: dosya metadata kaydedildi.</div>
            <div className="timeline-item">Is logu: musteriden ek bilgi beklentisi yok.</div>
          </div>
        </article>
      </section>
    </>
  )
}
