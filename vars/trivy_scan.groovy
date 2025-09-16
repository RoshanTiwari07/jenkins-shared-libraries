def call(String path = ".") {
    script {
        sh """
            docker run --rm \
              -v ${pwd()}:${path} \
              aquasec/trivy fs --severity HIGH,CRITICAL --exit-code 1 ${path}
        """
    }
}
