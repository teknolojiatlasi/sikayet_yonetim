-- Tam yetkili kullanici
-- Kullanici adi: superadmin
-- Sifre: super123
-- Not: Uygulama PasswordEncoderFactories kullandigi icin {noop} prefiksi ile duz sifre tanimlandi.

INSERT INTO kullanicilar (
    kullanici_adi,
    email,
    sifre_hash,
    ad,
    soyad,
    created_at,
    active
)
SELECT
    'superadmin',
    'superadmin@sikayet.local',
    '{noop}super123',
    'Super',
    'Admin',
    NOW(),
    TRUE
WHERE NOT EXISTS (
    SELECT 1
    FROM kullanicilar
    WHERE kullanici_adi = 'superadmin'
);

INSERT INTO rol_yetkileri (
    rol_id,
    yetki_id,
    created_at,
    active
)
SELECT
    r.id,
    y.id,
    NOW(),
    TRUE
FROM roller r
CROSS JOIN yetkiler y
WHERE r.kod = 'ROLE_ADMIN'
AND NOT EXISTS (
    SELECT 1
    FROM rol_yetkileri ry
    WHERE ry.rol_id = r.id
      AND ry.yetki_id = y.id
);

INSERT INTO kullanici_rolleri (
    kullanici_id,
    rol_id,
    created_at,
    active
)
SELECT
    k.id,
    r.id,
    NOW(),
    TRUE
FROM kullanicilar k
JOIN roller r ON r.kod IN ('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_INCELEYEN')
WHERE k.kullanici_adi = 'superadmin'
AND NOT EXISTS (
    SELECT 1
    FROM kullanici_rolleri kr
    WHERE kr.kullanici_id = k.id
      AND kr.rol_id = r.id
);
