#!/usr/bin/env groovy
// OpenShiftCredentialsID 'Service Account Token'
def call(String OpenshiftcredintialsID, String imageName, String openshiftServer, String openshiftProject) {
    
    // Update deployment configuration with new Docker Hub image tag
    sh "sed -i 's|image:.*|image: ${imageName}|g' java-deployment.yml"

        // withKubeConfig(caCertificate: '', clusterName: '', contextName: '', credentialsId: 'openshift', namespace: 'osamaayman', restrictKubeConfigAccess: false, serverUrl: 'https://api.ocp-training.ivolve-test.com:6443') {
        withKubeCredentials(kubectlCredentials: [[caCertificate: '', clusterName: '', contextName: '', credentialsId: 'token', namespace: 'osamaayman', serverUrl: 'https://api.ocp-training.ivolve-test.com:6443']]) {
        sh "oc apply -f java-deployment-service.yml"
    }
}




// #!/usr/bin/env groovy

// // KubernetesCredentialsID 'KubeConfig file'
// def call(String k8sCredentialsID, String imageName) {
    
//     // Update deployment.yaml with new Docker Hub image
//     sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' java-deployment.yml"

//     // login to k8s Cluster via KubeConfig file
//     withCredentials([file(credentialsId: "${k8sCredentialsID}", variable: 'KUBECONFIG_FILE')]) {
//         sh "export KUBECONFIG=${KUBECONFIG_FILE} && kubectl apply -f myapp-deployment.yml"
//     }
// }
