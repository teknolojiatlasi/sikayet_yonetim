import { axiosClient } from './axiosClient'

export type LoginPayload = { username: string; password: string }

export async function login(payload: LoginPayload) {
  const { data } = await axiosClient.post('/auth/login', payload)
  return data.data
}
