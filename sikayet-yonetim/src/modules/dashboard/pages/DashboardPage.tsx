import { useNavigate } from 'react-router-dom'

export function DashboardPage() {
  const navigate = useNavigate()

  return (
    <>
      <section className="page-header">
        <div>
          <div className="eyebrow">Dashboard</div>
          <h2 className="page-title">Operasyon Ozeti</h2>
          <div className="muted">Atama, durum ve sonuc akislarini anlik izleyin.</div>
        </div>
        <button className="button-primary" type="button" onClick={() => navigate('/complaints')}>
          Yeni sikayet
        </button>
      </section>

      <section className="stats">
        <article className="card"><div className="eyebrow">Toplam sikayet</div><div className="stat-value">1.248</div><div className="muted">Bu ay tum kayitlar</div></article>
        <article className="card"><div className="eyebrow">Acik kayit</div><div className="stat-value">348</div><div className="muted">Islem bekleyenler</div></article>
        <article className="card"><div className="eyebrow">Sonuclanan</div><div className="stat-value">27</div><div className="muted">Bugun kapatilanlar</div></article>
        <article className="card"><div className="eyebrow">Bana atanan</div><div className="stat-value">14</div><div className="muted">Aktif is listesi</div></article>
      </section>

      <section className="two-col">
        <article className="panel" style={{ flex: 1 }}>
          <div className="eyebrow">Durum Dagilimi</div>
          <div className="timeline">
            <div className="timeline-item">Yeni: 82</div>
            <div className="timeline-item">Atandi: 119</div>
            <div className="timeline-item">Incelemede: 147</div>
            <div className="timeline-item">Ek Bilgi Bekleniyor: 31</div>
          </div>
        </article>
        <article className="panel" style={{ flex: 1 }}>
          <div className="eyebrow">Son Hareketler</div>
          <div className="timeline">
            <div className="timeline-item">SY-2026-1042 numarali kayit Elif Acar kullanicisina atandi.</div>
            <div className="timeline-item">SY-2026-1041 kaydi icin PDF eki yuklendi.</div>
            <div className="timeline-item">Iki kayitta sonuc olusturulup SONUCLANDI durumuna gecildi.</div>
          </div>
        </article>
      </section>
    </>
  )
}
