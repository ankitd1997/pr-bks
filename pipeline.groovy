pipeline {
    agent any
    stages {
        stage ('code-pull') {
            steps {
                git branch: 'dev', url: 'https://github.com/ankitd1997/pr-bks.git'
            }
        }
        stage ('code-Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage ('Deploy-k8s') {
            steps {
                sh '''
                    docker build . -t ankit00398/spring-backend:latest
                    docker push ankit00398/spring-backend:latest
                    docker rmi ankit00398/spring-backend:latest
                    kubectl apply -f ./deploy/
                '''
                
            }
        }
        
    }
}
