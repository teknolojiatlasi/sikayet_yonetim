import { NavLink, Outlet } from 'react-router-dom'
import { useAuth } from '../../app/providers/AuthProvider'

const navItems = [
  { to: '/', label: 'Dashboard' },
  { to: '/complaints', label: 'Sikayetler' },
  { to: '/persons', label: 'Kisiler' },
  { to: '/users', label: 'Kullanicilar' },
]

export function AppShell() {
  const { username, logout } = useAuth()

  return (
    <div className="app-shell">
      <aside className="sidebar">
        <div className="brand">
          <span className="brand-tag">Kurumsal Platform</span>
          <h1>Sikayet Yonetim</h1>
          <div style={{ color: 'rgba(249, 242, 232, 0.68)' }}>
            Atama, durum akisi, audit ve dosya surecleri tek yerde.
          </div>
        </div>

        <nav className="sidebar-nav">
          {navItems.map((item) => (
            <NavLink
              key={item.to}
              className={({ isActive }) =>
                isActive ? 'nav-link active' : 'nav-link'
              }
              to={item.to}
              end={item.to === '/'}
            >
              {item.label}
            </NavLink>
          ))}
        </nav>

        <div className="sidebar-card">
          <strong>Aktif kullanici</strong>
          <div>{username ?? 'Tanimsiz'}</div>
          <div className="helper-text" style={{ marginTop: 8 }}>
            ROLE_ADMIN / SIKAYET_ASSIGN / LOG_VIEW
          </div>
        </div>

        <button className="button-secondary" onClick={logout} type="button">
          Oturumu kapat
        </button>
      </aside>

      <main className="main-content">
        <div className="topbar">
          <div>
            <div className="eyebrow">Sikayet operasyon merkezi</div>
            <div className="meta">
              Yeni kayit, atama, durum degisimi ve sonuc akisi burada yonetilir.
            </div>
          </div>
        </div>

        <Outlet />
      </main>
    </div>
  )
}
