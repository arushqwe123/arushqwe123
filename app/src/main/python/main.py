import requests
def main(curency_eamount,amount,conversion_country):
    curency_eamount = curency_eamount.upper()
    conversion_country = conversion_country.upper()
    key = "0d030a77b5609e68b17d1403"
    response = requests.get(f"https://v6.exchangerate-api.com/v6/{key}/latest/{curency_eamount}").json()
    ans  = float(amount)*response["conversion_rates"][conversion_country]
    return ans