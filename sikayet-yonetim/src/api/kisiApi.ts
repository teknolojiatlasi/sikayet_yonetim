import { axiosClient } from './axiosClient'

export interface Person {
  id?: string
  ad: string
  soyad: string
  telefon: string
  email: string
  aktif?: boolean
}

export async function getPersons() {
  const { data } = await axiosClient.get('/persons')
  return data.data
}

export async function createPerson(person: Person) {
  const { data } = await axiosClient.post('/persons', person)
  return data.data
}
