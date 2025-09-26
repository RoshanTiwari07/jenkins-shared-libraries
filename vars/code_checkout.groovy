def call(String url, String branch){
    echo "🔄 Checking out ${branch} from ${url}"
    git url: "${url}", branch: "${branch}"
    echo "✅ Checkout completed"
}
