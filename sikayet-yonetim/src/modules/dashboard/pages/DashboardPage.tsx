import { StatCard } from '../../../components/common/StatCard'

const recentActions = [
  'SY-2026-1042 numarali sikayet ROLE_INCELEYEN kullanicisine atandi.',
  'Ek bilgi beklenen iki kayit icin bildirim uretilmesi gerekiyor.',
  'Dosya yukleme ekraninda gecen hafta 41 PDF ve 13 JPG kabul edildi.',
]

export function DashboardPage() {
  return (
    <>
      <section className="page-header">
        <div>
          <h2>Operasyon ozeti</h2>
          <p>
            Ilk surum dashboard yapisi, atama ve sonuc surecini one cikaran KPI
            kartlariyla kuruldu.
          </p>
        </div>
        <button className="button-primary" type="button">
          Yeni sikayet ac
        </button>
      </section>

      <section className="grid-4">
        <StatCard label="Toplam sikayet" value="1.248" note="Bu ay acilan tum kayitlar" />
        <StatCard label="Acik kayit" value="348" note="Atama veya inceleme bekleyenler" />
        <StatCard label="Bugun sonuclanan" value="27" note="Kapanis akisi tamamlananlar" />
        <StatCard label="Bana atanan" value="14" note="Aktif kullanici aksiyon listesi" />
      </section>

      <section className="grid-2">
        <article className="panel">
          <div className="eyebrow">Surec gorunumu</div>
          <h3 className="section-title">Durum dagilimi</h3>
          <div className="status-list">
            <div className="status-row">
              <span>Yeni</span>
              <strong>82</strong>
            </div>
            <div className="status-row">
              <span>Atandi</span>
              <strong>119</strong>
            </div>
            <div className="status-row">
              <span>Incelemede</span>
              <strong>147</strong>
            </div>
            <div className="status-row">
              <span>Ek bilgi bekleniyor</span>
              <strong>31</strong>
            </div>
          </div>
        </article>

        <article className="panel">
          <div className="eyebrow">Islemsel log</div>
          <h3 className="section-title">Son hareketler</h3>
          <div className="timeline">
            {recentActions.map((action) => (
              <div className="timeline-item" key={action}>
                {action}
              </div>
            ))}
          </div>
        </article>
      </section>
    </>
  )
}
