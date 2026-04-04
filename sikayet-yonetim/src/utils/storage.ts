const TOKEN_KEY = 'sikayet-access-token'

export const storage = {
  getToken: () => window.localStorage.getItem(TOKEN_KEY),
  setToken: (token: string) => window.localStorage.setItem(TOKEN_KEY, token),
  clearToken: () => window.localStorage.removeItem(TOKEN_KEY),
}
