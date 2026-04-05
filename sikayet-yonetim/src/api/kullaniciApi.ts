import { axiosClient } from './axiosClient'

export interface User {
  id?: string
  kullaniciAdi: string
  ad: string
  soyad: string
  sifre?: string
  aktif?: boolean
}

export async function getUsers() {
  const { data } = await axiosClient.get('/users')
  return data.data
}

export async function createUser(user: User) {
  const { data } = await axiosClient.post('/users', user)
  return data.data
}
