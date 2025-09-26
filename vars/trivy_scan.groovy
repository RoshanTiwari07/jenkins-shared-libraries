def call() {
    sh """
        # Add common paths where trivy might be installed
        export PATH=\$PATH:/usr/local/bin:/usr/bin:/bin:/snap/bin
        
        # Try to find and use trivy
        if command -v trivy >/dev/null 2>&1; then
            trivy fs --severity HIGH,CRITICAL --format table --exit-code 1 .
        else
            echo "Trivy not found in PATH, trying Docker fallback..."
            docker run --rm -v \${PWD}:/src aquasec/trivy:latest fs --severity HIGH,CRITICAL --format table --exit-code 1 /src
        fi
    """
}
