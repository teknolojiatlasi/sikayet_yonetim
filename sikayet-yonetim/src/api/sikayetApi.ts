import { axiosClient } from './axiosClient'

export interface Complaint {
  id: string
  numarası: string
  tür: string
  durum: string
  başlık: string
  açıklama: string
  atanmışKişi?: string
  oluşturmaTarihi: string
}

export interface CreateComplaintRequest {
  typeCode: string
  title: string
  description: string
}

export async function getComplaints() {
  const { data } = await axiosClient.get('/complaints')
  return data.data
}

export async function getComplaint(id: string) {
  const { data } = await axiosClient.get(`/complaints/${id}`)
  return data.data
}

export async function createComplaint(request: CreateComplaintRequest) {
  const { data } = await axiosClient.post('/complaints', request)
  return data.data
}

export async function assignComplaint(id: string, userId: string) {
  const { data } = await axiosClient.post(`/complaints/${id}/assign`, { userId })
  return data.data
}

export async function changeComplaintStatus(id: string, statusCode: string) {
  const { data } = await axiosClient.post(`/complaints/${id}/change-status`, { statusCode })
  return data.data
}

export async function createComplaintResult(id: string, result: string) {
  const { data } = await axiosClient.post(`/complaints/${id}/result`, { result })
  return data.data
}

export async function getComplaintTypes() {
  const { data } = await axiosClient.get('/complaint-types')
  return data.data
}

export async function getComplaintStatuses() {
  const { data } = await axiosClient.get('/complaint-statuses')
  return data.data
}
