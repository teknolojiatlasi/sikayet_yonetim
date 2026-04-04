import { axiosClient } from './axiosClient'

export async function getComplaints() {
  const { data } = await axiosClient.get('/complaints')
  return data.data
}
