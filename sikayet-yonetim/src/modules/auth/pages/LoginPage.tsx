import { useForm } from 'react-hook-form'
import { useLocation, useNavigate } from 'react-router-dom'
import { useAuth } from '../../../app/providers/AuthProvider'

type LoginForm = {
  username: string
  password: string
}

export function LoginPage() {
  const navigate = useNavigate()
  const location = useLocation()
  const { login } = useAuth()
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm<LoginForm>({
    defaultValues: {
      username: 'admin',
      password: 'admin123',
    },
  })

  const onSubmit = async (values: LoginForm) => {
    await new Promise((resolve) => setTimeout(resolve, 300))
    login('mock-jwt-token', values.username)
    navigate(location.state?.from ?? '/', { replace: true })
  }

  return (
    <div className="login-layout">
      <section className="login-hero">
        <div className="eyebrow" style={{ color: '#ffc7b8' }}>
          Sikayet Yonetim Sistemi
        </div>
        <h1 style={{ fontSize: '3.4rem', marginBottom: 18 }}>
          Denetlenebilir is akisi ile kurumsal sikayet operasyonu
        </h1>
        <p style={{ maxWidth: 520, color: 'rgba(255, 246, 238, 0.78)' }}>
          JWT tabanli giris, rol ve yetki yonetimi, durum gecmisi, atama izi ve
          dosya akislarini tek panelde yoneten React istemcisi.
        </p>
      </section>

      <div className="login-panel-wrap">
        <div className="login-panel">
          <div className="eyebrow">Guvenli erisim</div>
          <h1>Oturum ac</h1>
          <div className="helper-text">
            Demo akisi icin varsayilan kullanici `admin / admin123`.
          </div>

          <form className="login-form" onSubmit={handleSubmit(onSubmit)}>
            <label className="field-group">
              <span>Kullanici adi</span>
              <input
                {...register('username', {
                  required: 'Kullanici adi zorunludur.',
                })}
                placeholder="admin"
              />
              {errors.username ? (
                <span className="error-text">{errors.username.message}</span>
              ) : null}
            </label>

            <label className="field-group">
              <span>Sifre</span>
              <input
                {...register('password', { required: 'Sifre zorunludur.' })}
                placeholder="••••••••"
                type="password"
              />
              {errors.password ? (
                <span className="error-text">{errors.password.message}</span>
              ) : null}
            </label>

            <button className="button-primary" disabled={isSubmitting} type="submit">
              {isSubmitting ? 'Giris yapiliyor...' : 'Panele gir'}
            </button>
          </form>
        </div>
      </div>
    </div>
  )
}
