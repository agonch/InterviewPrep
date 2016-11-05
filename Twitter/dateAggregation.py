import sys
from datetime import datetime, timedelta

def getActivityIndex(d, activity, date_to_activity):
    for i, (act, n) in enumerate(date_to_activity[d]):
        if activity == act:
            return i
    return -1
    
def main():
    start_date = end_date = None
    date_to_activity = {}
    for i, line in enumerate(sys.stdin):
        if i == 0:
            start_date, end_date = [datetime.strptime(d, "%Y-%m") for d in line.replace('\n', '').split(', ')]
        if i > 1:
            d, activity, num = line.replace('\n', '').split(', ')
            d = datetime.strptime(d, "%Y-%m-%d").replace(day=1) # ignore the day in this date
            if d >= start_date and d < end_date:
                if d in date_to_activity:
                    # check if activity already exists on this date (and aggregate counts)
                    idx = getActivityIndex(d, activity, date_to_activity)
                    if idx != -1:
                        date_to_activity[d][idx][1] += int(num) # aggregate and increment count
                    else:
                        date_to_activity[d].append([activity, int(num)]) # a new activity for this date
                else:
                    # d is a new date never seen before
                    date_to_activity[d] = [[activity, int(num)]]
    
    # print the results
    dates_in_range = list(date_to_activity.keys())
    dates_in_range.sort()
    dates_in_range.reverse()
    for d in dates_in_range:
        aggregated_activities = [activity + ', ' + str(num) for activity, num in date_to_activity[d]]
        aggregated_activities = ', '.join(sorted(aggregated_activities))
        print("{:%Y-%m}".format(d) + ', ' + aggregated_activities)

main()
