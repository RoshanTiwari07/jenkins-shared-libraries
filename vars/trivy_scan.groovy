def call() {
    sh """
        echo "🔍 Running Trivy security scan..."
        trivy fs --severity HIGH,CRITICAL --format table --exit-code 0 .
        echo "✅ Trivy scan completed"
    """
}
