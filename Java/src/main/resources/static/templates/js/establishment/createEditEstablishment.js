$(document).ready(
    function () {
        let disabledDays = []

        if ($("[name = 'schedule']").length === 0) {
            addNewSchedule()
        } else {
            checkScheduleForEnableDelete()
        }
        initialization()

        function initialization() {
            initializationEvents();
            initializationElements();
        }

        function initializationEvents() {
            $("#addSchedule").unbind("click").click(
                function () {
                    addNewSchedule()
                }
            )

            $("[name = 'deleteSchedule']").unbind("click").click(
                function () {
                    $(this).closest($("[name='schedule']")).remove()
                    checkScheduleForEnableDelete()
                    checkScheduleForEnableAddSchedule()
                }
            )

            $("[name = 'beginDay']").unbind("click").click(function () {
                checkDaysOfTheWeekForDisabled()
                refreshBeginDaysSelect(this)
            })
            $("[name = 'finishDay']").unbind("click").click(function () {
                checkDaysOfTheWeekForDisabled()
                refreshFinishDaysSelect(this)
            })

            $("[name = 'beginDay']").unbind("change").change(function () {
                checkDaysOfTheWeekForDisabled()
                checkScheduleForEnableAddSchedule()

            })
            $("[name = 'finishDay']").unbind("change").change(function () {
                checkDaysOfTheWeekForDisabled()
                checkScheduleForEnableAddSchedule()
            })

        }

        function initializationElements() {
            let flatpickrs = $(".flatpickr")
            for (let currentFlatpickrs of flatpickrs) {
                if (currentFlatpickrs.value === '')
                    flatpickr(currentFlatpickrs, {
                        defaultDate: "00:00",
                        enableTime: true,
                        noCalendar: true,
                        dateFormat: "H:i",
                        time_24hr: true
                    })
            }
            flatpickr(".flatpickr:not([value=''])", {
                enableTime: true,
                noCalendar: true,
                dateFormat: "H:i",
                time_24hr: true
            })

            $(".phone-mask").mask("+380(99)999-99-99");
        }

        function refreshBeginDaysSelect(thisElement) {
            let beginDays = $("[name = 'beginDay']")
            let beginDaySelectedOption
            let enabledBeginDay = true
            let currentFinishDayValue

            beginDaySelectedOption = thisElement.selectedOptions[0]
            $(thisElement.options).remove()
            $(thisElement).append($(beginDaySelectedOption))

            for (let i = 0; i < daysOfTheWeekEnum.length; i++) {
                for (let beginDay of beginDays) {
                    if (beginDay.value === thisElement.value) {
                        enabledBeginDay = true
                        continue
                    }
                    currentFinishDayValue = $(beginDay).parent().siblings().children("[name = 'finishDay']").val()
                    if (daysOfTheWeekEnum.indexOf(beginDay.value) === -1
                        || ((daysOfTheWeekEnum.indexOf(beginDay.value) < daysOfTheWeekEnum.indexOf(daysOfTheWeekEnum[i])

                                && (daysOfTheWeekEnum.indexOf(currentFinishDayValue) < daysOfTheWeekEnum.indexOf(daysOfTheWeekEnum[i])
                                    || currentFinishDayValue === ''))
                            || (daysOfTheWeekEnum.indexOf(beginDay.value) > daysOfTheWeekEnum.indexOf(daysOfTheWeekEnum[i])

                                && (daysOfTheWeekEnum.indexOf(currentFinishDayValue) > daysOfTheWeekEnum.indexOf(daysOfTheWeekEnum[i])
                                    || currentFinishDayValue === ''))
                        )) {
                        if (!disabledDays.includes(daysOfTheWeekEnum[i])) {
                            enabledBeginDay = true
                        } else {
                            enabledBeginDay = false
                            break
                        }
                    } else {
                        enabledBeginDay = false
                        break
                    }
                }

                if (enabledBeginDay) {
                    $(thisElement).append("<option value=" + daysOfTheWeekEnum[i] + ">" + daysOfTheWeekEnumValues[i] + "</option>")
                }
                enabledBeginDay = true
            }
        }

        function refreshFinishDaysSelect(thisElement) {

            let finishDays = $("[name = 'finishDay']")
            let siblingBeginDayValue = $(thisElement).parent().siblings().children("[name = 'beginDay']").val()
            let finishDaySelectedOption
            let currentBeginDayValue
            let enabledFinishDay = true

            finishDaySelectedOption = thisElement.selectedOptions[0]
            $(thisElement.options).remove()
            $(thisElement).append($(finishDaySelectedOption))

            for (let i = 0; i < daysOfTheWeekEnum.length; i++) {
                for (let finishDay of finishDays) {
                    if (finishDay.value === thisElement.value && finishDay.value !== '') {
                        enabledFinishDay = true
                        continue
                    }
                    currentBeginDayValue = $(finishDay).parent().siblings().children("[name = 'beginDay']").val()

                    if (daysOfTheWeekEnum.indexOf(siblingBeginDayValue) < daysOfTheWeekEnum.indexOf(daysOfTheWeekEnum[i])) {
                        if (
                            (daysOfTheWeekEnum.indexOf(currentBeginDayValue) <= daysOfTheWeekEnum.indexOf(siblingBeginDayValue)
                                && daysOfTheWeekEnum.indexOf(currentBeginDayValue) < daysOfTheWeekEnum.indexOf(daysOfTheWeekEnum[i])
                                && ((finishDay.value === ''
                                        || ((daysOfTheWeekEnum.indexOf(finishDay.value) < daysOfTheWeekEnum.indexOf(siblingBeginDayValue))
                                            && (daysOfTheWeekEnum.indexOf(finishDay.value) < daysOfTheWeekEnum.indexOf(daysOfTheWeekEnum[i]))
                                        )
                                    )
                                )
                            )
                            || (daysOfTheWeekEnum.indexOf(currentBeginDayValue) >= daysOfTheWeekEnum.indexOf(siblingBeginDayValue)
                                && ((finishDay.value !== ''
                                    && (daysOfTheWeekEnum.indexOf(currentBeginDayValue) > daysOfTheWeekEnum.indexOf(daysOfTheWeekEnum[i])
                                        && (daysOfTheWeekEnum.indexOf(finishDay.value) > daysOfTheWeekEnum.indexOf(daysOfTheWeekEnum[i]))
                                    )
                                )
                                || (finishDay.value === ''
                                    && (daysOfTheWeekEnum.indexOf(currentBeginDayValue) > daysOfTheWeekEnum.indexOf(daysOfTheWeekEnum[i]))
                                ))
                            )
                        ) {
                            if (!disabledDays.includes(daysOfTheWeekEnum[i])) {
                                enabledFinishDay = true
                            } else {
                                enabledFinishDay = false
                                break
                            }
                        } else {
                            enabledFinishDay = false
                            break
                        }
                    } else {
                        enabledFinishDay = false
                        break
                    }
                }

                if (enabledFinishDay) {
                    $(thisElement).append("<option value=" + daysOfTheWeekEnum[i] + ">" + daysOfTheWeekEnumValues[i] + "</option>")
                }
                enabledFinishDay = true
            }

            let hasEmptyValue = false
            for (let option of thisElement.options) {
                if (option.value === "") {
                    hasEmptyValue = true
                }
            }
            if (!hasEmptyValue) {
                $(thisElement).append("<option> - </option>")
            }

        }

        function checkDaysOfTheWeekForDisabled() {
            disabledDays.splice(0)
            let beginDays = $("[name = 'beginDay']")
            let finishDays = $("[name = 'finishDay']")
            let scheduleSelectors = $("[name='schedule']")
            for (let j = 0; j < beginDays.length; j++) {
                for (let i = 0; i < daysOfTheWeekEnum.length; i++) {
                    if (!disabledDays.includes(daysOfTheWeekEnum[i])
                        && ($(scheduleSelectors[j]).find(beginDays).val() === daysOfTheWeekEnum[i]
                            || ($(scheduleSelectors[j]).find(finishDays).val() !== ''
                                && daysOfTheWeekEnum.indexOf($(scheduleSelectors[j]).find(finishDays).val(), i) !== -1
                                && daysOfTheWeekEnum.indexOf($(scheduleSelectors[j]).find(finishDays).val(), i) <= daysOfTheWeekEnum.indexOf(daysOfTheWeekEnum[i], i)))
                    ) {
                        disabledDays.push(daysOfTheWeekEnum[i])
                    }
                }
            }
        }

        function addNewSchedule() {
            $.ajax(
                {
                    url: "/establishment/get-new-schedule",
                    success: function (form) {
                        $("#placeForSchedule").append(form)
                        initialization();
                        checkScheduleForEnableDelete()
                    }
                }
            )
        }

        function checkScheduleForEnableAddSchedule() {
            let beginDays = $("[name = 'beginDay']")
            let currentFinishDayValue
            let countDays = 0

            for (let beginDay of beginDays) {
                currentFinishDayValue = $(beginDay).parent().siblings().children("[name = 'finishDay']").val()
                if (currentFinishDayValue !== '') {
                    for (let i = daysOfTheWeekEnum.indexOf(beginDay.value); i <= daysOfTheWeekEnum.indexOf(currentFinishDayValue); i++) {
                        countDays++;
                    }
                } else {
                    countDays++
                }
            }
            if (countDays === 7) {
                $("#addSchedule").attr("disabled", "disabled")
            } else {
                $("#addSchedule").removeAttr("disabled")
            }
        }

        function checkScheduleForEnableDelete() {
            if ($("[name = 'schedule']").length <= 1) {
                $("[name = 'deleteSchedule']").attr("disabled", "disabled")
            } else {
                $("[name = 'deleteSchedule']").removeAttr("disabled")
            }
        }

        $('#selectIdCity').select2({
            minimumInputLength: 1,
            ajax: {
                delay: 250,
                minimumInputLength: 2,
                url: '/city/name-start-with',
                data: function (params) {
                    return {
                        cityName: params.term,
                    }
                },
                processResults: function (data) {
                    return {
                        results: $.map(data, function (item) {
                            return {
                                text: item.name,
                                id: item.idCity
                            }
                        })
                    };
                }
            }
        });

        $('#selectIdStreet').select2({
            minimumInputLength: 1,
            ajax: {
                delay: 250,
                url: '/street/name-start-with',
                data: function (params) {
                    return {
                        streetName: params.term,
                    }
                },
                processResults: function (data) {
                    return {
                        results: $.map(data, function (item) {
                            return {
                                text: item.name,
                                id: item.idStreet
                            }
                        })
                    };
                }
            }
        });
    }
)