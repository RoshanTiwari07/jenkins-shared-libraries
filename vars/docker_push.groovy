def call(String imageName, String tag, String credentialId) {
    withCredentials([usernamePassword(credentialsId: credentialId,
                                    usernameVariable: 'DOCKER_USERNAME', 
                                    passwordVariable: 'DOCKER_PASSWORD')]) {
        sh """
            echo "🐳 Logging into Docker Hub..."
            echo \${DOCKER_PASSWORD} | docker login -u \${DOCKER_USERNAME} --password-stdin
            
            echo "📤 Pushing ${imageName}:${tag}..."
            docker push ${imageName}:${tag}
            
            echo "📤 Pushing ${imageName}:latest..."
            docker push ${imageName}:latest
            
            echo "✅ Docker push completed for ${imageName}"
        """
    }
}
