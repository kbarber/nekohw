Running from Source
===================

Install the Android SDK, and the 4.2.2 components.

Using the ADV (android adv), create a virtual image for a platform that supports 4.2.2. I recommend using the Google Nexus 4 device. 

Install the lein-droid plugin: <https://github.com/clojure-android/lein-droid/wiki/Tutorial>

Then run:

    $ lein droid doall
