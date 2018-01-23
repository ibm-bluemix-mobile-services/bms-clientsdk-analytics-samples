cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
  {
    "id": "bms-core.BMSClient",
    "file": "plugins/bms-core/www/BMSClient.js",
    "pluginId": "bms-core",
    "clobbers": [
      "BMSClient"
    ]
  },
  {
    "id": "bms-core.BMSRequest",
    "file": "plugins/bms-core/www/BMSRequest.js",
    "pluginId": "bms-core",
    "clobbers": [
      "BMSRequest"
    ]
  },
  {
    "id": "bms-core.BMSLogger",
    "file": "plugins/bms-core/www/BMSLogger.js",
    "pluginId": "bms-core",
    "clobbers": [
      "BMSLogger"
    ]
  },
  {
    "id": "bms-core.BMSAnalytics",
    "file": "plugins/bms-core/www/BMSAnalytics.js",
    "pluginId": "bms-core",
    "clobbers": [
      "BMSAnalytics"
    ]
  },
  {
    "id": "bms-core.BMSAuthorizationManager",
    "file": "plugins/bms-core/www/BMSAuthorizationManager.js",
    "pluginId": "bms-core",
    "clobbers": [
      "BMSAuthorizationManager"
    ]
  },
  {
    "id": "cordova-plugin-geolocation.Coordinates",
    "file": "plugins/cordova-plugin-geolocation/www/Coordinates.js",
    "pluginId": "cordova-plugin-geolocation",
    "clobbers": [
      "Coordinates"
    ]
  },
  {
    "id": "cordova-plugin-geolocation.PositionError",
    "file": "plugins/cordova-plugin-geolocation/www/PositionError.js",
    "pluginId": "cordova-plugin-geolocation",
    "clobbers": [
      "PositionError"
    ]
  },
  {
    "id": "cordova-plugin-geolocation.Position",
    "file": "plugins/cordova-plugin-geolocation/www/Position.js",
    "pluginId": "cordova-plugin-geolocation",
    "clobbers": [
      "Position"
    ]
  },
  {
    "id": "cordova-plugin-geolocation.geolocation",
    "file": "plugins/cordova-plugin-geolocation/www/geolocation.js",
    "pluginId": "cordova-plugin-geolocation",
    "clobbers": [
      "navigator.geolocation"
    ]
  }
];
module.exports.metadata = 
// TOP OF METADATA
{
  "cordova-plugin-cocoapod-support": "1.2.10",
  "cordova-plugin-add-swift-support": "1.7.0",
  "bms-core": "2.3.8",
  "cordova-plugin-compat": "1.2.0",
  "cordova-plugin-geolocation": "2.4.3",
  "cordova-plugin-whitelist": "1.3.2"
};
// BOTTOM OF METADATA
});