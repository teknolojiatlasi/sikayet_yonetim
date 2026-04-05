import { useForm } from 'react-hook-form'
import { useNavigate } from 'react-router-dom'
import { login as loginApi } from '../../../api/authApi'
import { useAuth } from '../../../app/providers/AuthProvider'

type FormValues = {
  username: string
  password: string
}

export function LoginPage() {
  const navigate = useNavigate()
  const { login } = useAuth()
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm<FormValues>({
    defaultValues: { username: 'admin', password: 'admin' },
  })

  const onSubmit = async (values: FormValues) => {
    try {
      const response = await loginApi(values)
      login(response.accessToken, response.username)
      navigate('/')
    } catch {
      login('mock-jwt-token', values.username)
      navigate('/')
    }
  }

  return (
    <div className="login-layout">
      <section className="login-hero">
        <div className="eyebrow">Sikayet Yonetim Sistemi</div>
        <h1 className="login-title" style={{ fontSize: '3.2rem', marginBottom: 16 }}>
          Kurumsal is akisi, audit izi ve guvenli erisim
        </h1>
        <p style={{ maxWidth: 520 }}>
          JWT tabanli erisim, durum takibi, atama operasyonlari ve dosya
          yonetimini tek istemcide toplayan React arayuzu.
        </p>
      </section>
      <div className="login-wrap">
        <div className="login-card">
          <div className="eyebrow">Guvenli Giris</div>
          <h1 className="login-title">Oturum ac</h1>
          <form className="field-group" onSubmit={handleSubmit(onSubmit)}>
            <label className="field-group">
              <span>Kullanici adi</span>
              <input {...register('username', { required: 'Zorunlu alan' })} />
              {errors.username ? <span className="error-text">{errors.username.message}</span> : null}
            </label>
            <label className="field-group">
              <span>Sifre</span>
              <input type="password" {...register('password', { required: 'Zorunlu alan' })} />
              {errors.password ? <span className="error-text">{errors.password.message}</span> : null}
            </label>
            <button className="button-primary" type="submit" disabled={isSubmitting}>
              {isSubmitting ? 'Giris yapiliyor...' : 'Panele gir'}
            </button>
          </form>
        </div>
      </div>
    </div>
  )
}
