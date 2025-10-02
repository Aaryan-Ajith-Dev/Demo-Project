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
}
