import { NavLink, Outlet } from 'react-router-dom'
import { useAuth } from '../../app/providers/AuthProvider'

const links = [
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
          <div className="muted" style={{ color: 'rgba(255,255,255,.72)' }}>
            Sikayet, atama, sonuc ve audit akislarini tek panelde yonetin.
          </div>
        </div>

        <nav className="sidebar-nav">
          {links.map((link) => (
            <NavLink
              key={link.to}
              to={link.to}
              end={link.to === '/'}
              className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}
            >
              {link.label}
            </NavLink>
          ))}
        </nav>

        <div className="card">
          <div className="eyebrow">Aktif kullanici</div>
          <strong>{username ?? 'Bilinmiyor'}</strong>
        </div>

        <button className="button-secondary" type="button" onClick={logout}>
          Cikis yap
        </button>
      </aside>

      <main className="main">
        <Outlet />
      </main>
    </div>
  )
}
