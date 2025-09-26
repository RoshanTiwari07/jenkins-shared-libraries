def call() {
    sh """
        trivy fs --severity HIGH,CRITICAL --format table --exit-code 1 .
    """
}
