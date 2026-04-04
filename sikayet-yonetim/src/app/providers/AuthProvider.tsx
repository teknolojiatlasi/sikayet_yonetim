import {
  createContext,
  useContext,
  useMemo,
  useState,
} from 'react'
import type { ReactNode } from 'react'
import { storage } from '../../utils/storage'

type AuthContextType = {
  isAuthenticated: boolean
  username: string | null
  login: (token: string, username: string) => void
  logout: () => void
}

const AuthContext = createContext<AuthContextType | undefined>(undefined)

export function AuthProvider({ children }: { children: ReactNode }) {
  const [token, setToken] = useState<string | null>(() => storage.getToken())
  const [username, setUsername] = useState<string | null>(() =>
    token ? 'admin' : null,
  )

  const value = useMemo(
    () => ({
      isAuthenticated: Boolean(token),
      username,
      login: (nextToken: string, nextUsername: string) => {
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
    throw new Error('AuthProvider gerekli')
  }
  return context
}
