type StatCardProps = {
  label: string
  value: string
  note: string
}

export function StatCard({ label, value, note }: StatCardProps) {
  return (
    <article className="stat-card">
      <div className="eyebrow">{label}</div>
      <div className="value">{value}</div>
      <div className="helper-text">{note}</div>
    </article>
  )
}
