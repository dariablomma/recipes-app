import styles from "./RecipesCard.module.scss"

export function RecipesCard () {
    return (
        <div className={styles.card}>
            <h3 className={styles.title}>Recipe title</h3>
            <p className={styles.description}>Some
            description in several lines
            </p>
            <a href="#">External link</a>
            <div className={styles.tags}>
                <div className={styles.tag}>Tag 1</div>
                <div className={styles.tag}>Tag 2</div>
            </div>
            <div className={styles.footer}>
                <div className={[styles.actionBtn, styles.edit].join(' ')}>Edit</div>
                <div className={[styles.actionBtn, styles.delete].join(' ')}>Delete</div>
                <div className={[styles.actionBtn, styles.view].join(' ')}>View</div>
            </div>
        </div>
    )
}