# Fetch Rewards Coding Exercise

App that retrieves the data from https://fetch-hiring.s3.amazonaws.com/hiring.json.

Display list of items based on the following requirements:

- All the items are grouped by "listId"
- Results are sorted first by "listId" then by "name" when displaying.
- Filtered out items where "name" is blank or null.

As listID is a string variable it is been sorted alphabetically.
Example if list is [Item 10, Item 4, Item 11] the resultant list after sorting will be [Item 10, Item 11, Item 4] as sorting is done on string value.

Architechture Used: MVVM

# Internet Connectivity
If internet connection is lost, App will try to reconnect once the device is online.

# Screenshots
![ScreenShot](https://github.com/JuileePanse/FetchRewardsAssessment/blob/master/Screenshot_20210214-145555_FetchRewardsAssessment.jpg)

