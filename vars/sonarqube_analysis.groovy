def call(String sonarQubeServer, String projectName, String projectKey) {
    withCredentials([string(credentialsId: 'Sonar', variable: 'SONAR_TOKEN')]) {
        withSonarQubeEnv(sonarQubeServer) {
            sh """
                ${SONAR_HOME}/bin/sonar-scanner \
                -Dsonar.projectName=${projectName} \
                -Dsonar.projectKey=${projectKey} \
                -Dsonar.token=\${SONAR_TOKEN}
            """
        }
    }
}
