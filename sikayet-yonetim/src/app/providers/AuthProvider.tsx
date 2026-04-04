import {
  createContext,
  useContext,
  useMemo,
  useState,
} from 'react'
import type { ReactNode } from 'react'
import { storage } from '../../utils/storage'

type AuthContextValue = {
  isAuthenticated: boolean
  username: string | null
  login: (token: string, username: string) => void
  logout: () => void
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined)

export function AuthProvider({ children }: { children: ReactNode }) {
  const [token, setToken] = useState<string | null>(() => storage.getToken())
  const [username, setUsername] = useState<string | null>(() =>
    token ? 'admin' : null,
  )

  const value = useMemo<AuthContextValue>(
    () => ({
      isAuthenticated: Boolean(token),
      username,
      login: (nextToken, nextUsername) => {
        storage.setToken(nextToken)
        setToken(nextToken)
        setUsername(nextUsername)
      },
      logout: () => {
        storage.clearToken()
        setToken(null)
        setUsername(null)
      },
    }),
    [token, username],
  )

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
  const context = useContext(AuthContext)

  if (!context) {
    throw new Error('useAuth must be used within AuthProvider')
  }

  return context
}
