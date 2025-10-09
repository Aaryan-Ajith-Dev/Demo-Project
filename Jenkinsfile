pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t a3ryn/calculator-app:latest .'
                sh 'docker push a3ryn/calculator-app:latest'
            }
        }
        stage('Run Container') {
            steps {
                sh 'ansible-playbook -i ansible/inventory.ini ansible/playbook.yml'
            }
        }
    }

    post {
        success {
            mail to: 'aaryandave2004@gmail.com',
                 subject: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                 body: "Good news! The Jenkins job '${env.JOB_NAME}' build #${env.BUILD_NUMBER} succeeded.\n\nCheck the details at: ${env.BUILD_URL}"
        }
        failure {
            mail to: 'aaryandave2004@gmail.com',
                 subject: "FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                 body: "Oops! The Jenkins job '${env.JOB_NAME}' build #${env.BUILD_NUMBER} failed.\n\nCheck the details at: ${env.BUILD_URL}"
        }
        unstable {
            mail to: 'aaryandave2004@gmail.com',
                 subject: "UNSTABLE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                 body: "The Jenkins job '${env.JOB_NAME}' build #${env.BUILD_NUMBER} is unstable.\n\nCheck the details at: ${env.BUILD_URL}"
        }
    }
}
