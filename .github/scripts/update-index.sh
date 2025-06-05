set -e

mkdir -p tmp-gh-pages
cd tmp-gh-pages

git init

git remote add origin https://github.com/${GITHUB_REPOSITORY}.git
git fetch origin gh-pages
git checkout origin/gh-pages

REPORT_PATHS=$(find . -mindepth 2 -maxdepth 2 -type d | grep -E "(master|nightly|pr-report)/[0-9]+$" | sort -r)

cat <<EOF > index.html
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Allure Reports</title>
</head>
<body>
  <h1>Allure Reports</h1>
  <ul>
EOF

for path in $REPORT_PATHS; do
  echo "    <li><a href=\"$path/index.html\">$path</a></li>" >> index.html
done

cat <<EOF >> index.html
  </ul>
</body>
</html>
EOF

git config user.name "github-actions[bot]"
git config user.email "github-actions[bot]@users.noreply.github.com"
git add index.html
git commit -m "Update index.html [skip ci]" || echo "No changes"
git remote set-url origin https://x-access-token:${GITHUB_TOKEN}@github.com/${GITHUB_REPOSITORY}.git
git push origin HEAD:gh-pages