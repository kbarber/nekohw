(defproject nekohw/nekohw "0.0.1-SNAPSHOT"
  :description "FIXME: Android project description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"

  :global-vars {*warn-on-reflection* true}

  :source-paths ["src/clojure"]
  :java-source-paths ["src/java" "gen"]

  :dependencies [[android/clojure "1.5.0"]
                 [neko/neko "2.0.0-beta3"]
                 [org.codehaus.jsr166-mirror/jsr166y "1.7.0"]]

  :profiles {:dev {:dependencies [[android/tools.nrepl "0.2.0-bigstack"]]
                   :android {:aot :all-with-unused}}
             :release {:android {:aot :all}}}

  :android {:target-version "17"
            :aot-exclude-ns ["clojure.parallel"]})
