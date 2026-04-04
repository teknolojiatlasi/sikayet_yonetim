import { Navigate, Route, Routes } from 'react-router-dom'
import { AppShell } from '../../components/layout/AppShell'
import { LoginPage } from '../../modules/auth/pages/LoginPage'
import { DashboardPage } from '../../modules/dashboard/pages/DashboardPage'
import { PersonsPage } from '../../modules/kisiler/pages/PersonsPage'
import { UsersPage } from '../../modules/kullanicilar/pages/UsersPage'
import { ComplaintsPage } from '../../modules/sikayetler/pages/ComplaintsPage'
import { ProtectedRoute } from './ProtectedRoute'

export function AppRouter() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route element={<ProtectedRoute />}>
        <Route element={<AppShell />}>
          <Route index element={<DashboardPage />} />
          <Route path="/complaints" element={<ComplaintsPage />} />
          <Route path="/persons" element={<PersonsPage />} />
          <Route path="/users" element={<UsersPage />} />
        </Route>
      </Route>
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  )
}
