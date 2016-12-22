(defproject query-ut "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {}

  :dependencies [[org.clojure/clojure "1.8.0"]]

  :main ^:skip-aot main
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}

  :source-paths ["src"]
)
