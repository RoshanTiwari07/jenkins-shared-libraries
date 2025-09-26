def call() {
    sh """
        # Set paths where tools are installed
        export PATH=\$PATH:/var/lib/jenkins/tools:/usr/local/bin:/usr/bin
        
        # Try trivy first
        if /var/lib/jenkins/tools/trivy --version >/dev/null 2>&1; then
            echo "✅ Using Trivy installed at /var/lib/jenkins/tools/trivy"
            /var/lib/jenkins/tools/trivy fs --severity HIGH,CRITICAL --format table --exit-code 1 .
        elif command -v docker >/dev/null 2>&1; then
            echo "✅ Using Docker fallback for Trivy"
            docker run --rm -v \${PWD}:/src aquasec/trivy:latest fs --severity HIGH,CRITICAL --format table --exit-code 1 /src
        else
            echo "⚠️ Neither Trivy nor Docker available, skipping scan"
        fi
    """
}
