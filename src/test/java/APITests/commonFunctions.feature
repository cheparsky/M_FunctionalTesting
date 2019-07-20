@ignore
Feature: Common functions

  Scenario: Common functions

    * def currentDate =
          """
              function(){
                return new Date().toISOString().slice(0,10);
              }
          """

                # Function generates date in YYYY-MM-DD'T'HH:mm:ss.SSS'Z' format in the future based on current date and supplied parameter
    * def futureDateScopeTimeLimit =
            """
          function(days){
            return new Date(new Date().setDate(new Date().getDate() + days)).toISOString()
            }
           """

            # Function generates date in YYYY-MM-DD'T'HH:mm:ss.SSS'Z' format in the past based on current date and supplied parameter
    * def pastDateScopeTimeLimit =
            """
          function(days){
            return new Date(new Date().setDate(new Date().getDate() - days)).toISOString()
            }
           """

    * def randomString =
    """

    function randomString(amount) {
      var text = "";
      var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

      for (var i = 0; i < amount; i++)
        text += possible.charAt(Math.floor(Math.random() * possible.length));

      return text;
}
    """

    * def randomSpecialSymbols =
"""
    function randomSpecialSymbols(amount) {
      var text = "";
      var possible = "!@#$%^&*()_+-=|\?/€[]{};:.>,<`";
      for (var i = 0; i < amount; i++)
        text += possible.charAt(Math.floor(Math.random() * possible.length));
      return text; }
    """

        #functions that contains list of headers

    * def sessionHeaders = {'X-CSRF-TOKEN':'qfrWetTpXDpNhBcfPictLnj6W9cAYHW0lmGWiAyEykY=','Content-Type':'application/json;charset=UTF-8','Accept':'*/*'}
    * def employeesHeaders = {"Accept":"application/json",  "Accept-Encoding":"gzip, deflate",  "Accept-Language":"pl,en;q=0.9,ru;q=0.8",  "Content-Type":"application/json",  "DNT":"1",  "Origin":"http://cafetownsend-angular-rails.herokuapp.com",  "Referer":"http://cafetownsend-angular-rails.herokuapp.com/employees/new",  "X-CSRF-TOKEN":"qfrWetTpXDpNhBcfPictLnj6W9cAYHW0lmGWiAyEykY=",  "X-Requested-With":"XMLHttpRequest",  "User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36",'cookie':'_CafeTownsend-Angular-Rails_session=N1hPOHpXejFhcjZYYTh1OGg4cXEwYWF3Y0pOMldxMkRHWllMYUl5WE5yTDFpc3hQMCtJQmUrUXNvczMxZi8rUlR3enExL05VZWVvcEZ2SzZNVCtLcDBLWm1JMWNoTkdreXdTUm03ZkVKSitWYTZJM2UxcVN6eFVPTnpNK3lOQzZzK2M1WSsxWmVZU2licExLUzg3OTV3ZW9TWXhSYXMyOW9aZ01xQktiRkFqeHhqSUZ1YlNjcGgwcXVEeUIyVUw2bEFCNUtodnRWZXJJMkpEbEFiMHNWdz09LS1lWkV3MGFLZFJLWnQ2REMxa0JSbVdnPT0%3D--7943e85b5f6bcea96757e0620649a16dd2a60b8d'}

        #functions that contains list of params

    * def sessionParams = {"name":"Luke", "password":"Skywalker", "authorized":false}

        #functions that contains list of bodies

    * def newEmployeeBody = {"employee":{"first_name":"thgfhdfghdf","last_name":"dfghdfghdfgh","email":"dfsdf@adas","start_date":"2022-01-01"}}


