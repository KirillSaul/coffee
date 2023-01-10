$(document).ready(
    function () {
        /**
         * Common part
         *
         */

        function initializationCommonEvents() {
            $("[name = 'editEstablishment']").click(
                function () {
                    window.location = "/establishment/edit/" + parseInt(this.id)
                }
            )
        }


        /**
         *
         * active part
         *
         */
        requestUpdateEstablishmentActivePart()

        function initializationActiveEvents() {
            $("#selectCityEstablishmentsActive").change(() => requestUpdateEstablishmentActivePart())
            $("#activePageSize").change(
                function () {
                    changeActiveEstablishmentsSizeTable(this)
                }
            )
            $("[name = 'paginationActiveEstablishment']").click(
                function () {
                    clickActivePagination(this)
                }
            )
            $("[name = 'deleteEstablishment']").click(
                function () {
                    requestDeleteEstablishment(this)
                }
            )
            $("[name = 'editEstablishment']").click(
                function () {

                }
            )
        }

        $('#selectCityEstablishmentsActive').select2({
            minimumInputLength: 1,
            ajax: {
                delay: 250,
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

        function requestUpdateEstablishmentActivePart() {
            $.ajax(
                {
                    url: "/establishment/active",
                    method: "get",
                    data: $("#activeForm").serialize(),
                    success: function (establishments) {
                        updateEstablishmentsActiveTable(establishments.content)
                        updateActivePagination(establishments.totalPages, establishments.pageable.pageNumber)
                        initializationActiveEvents()
                        initializationCommonEvents()
                    }
                }
            )
        }

        function cleanActiveEstablishmentTable() {
            $("#activeTableBody").empty()
        }

        function updateEstablishmentsActiveTable(establishments) {
            cleanActiveEstablishmentTable()
            for (let establishment of establishments) {
                $("#activeTableBody").append(
                    $(" <tr>" +
                        "<td>" + establishment.cityName + "</td>" +
                        "<td>" + establishment.streetName + "</td>" +
                        "<td>" + establishment.streetNumber + "</td>" +
                        "<td>" +
                        "     <button type='button'" +
                        "             id=" + establishment.idEstablishment + "editEstablishment" +
                        "             name='editEstablishment'" +
                        "             class='btn btn-info'" +
                        "      > " +
                        "     Редактировать" +
                        "     </button>" +
                        "     <button id=" + establishment.idEstablishment + "deleteEstablishment" +
                        "             name='deleteEstablishment'" +
                        "             type='button' " +
                        "             class='btn btn btn-danger'" +
                        "     >" +
                        "     Удалить" +
                        "     </button>" +
                        "</td>" +
                        "</tr>")
                )
            }
        }

        function clickActivePagination(element) {
            if (!$(element).hasClass("disabled") && !$(element).hasClass("active")) {
                $(element).addClass("disabled")
                cleanActiveEstablishmentTable()
                $(element).siblings($(".active")).removeClass("active")
                $("#activeEstablishmentPage" + element.value).addClass("active")
                $("#activePage").val(element.value)
                if (element.value === $("#toStartPaginationActiveEstablishment").val()) {
                    $("#toStartPaginationActiveEstablishment").addClass("disabled")
                    $("#toEndPaginationActiveEstablishment").removeClass("disabled")
                } else if (element.value === $("#toEndPaginationActiveEstablishment").val()) {
                    $("#toEndPaginationActiveEstablishment").addClass("disabled")
                    $("#toStartPaginationActiveEstablishment").removeClass("disabled")
                } else {
                    $("#toEndPaginationActiveEstablishment").removeClass("disabled")
                    $("#toStartPaginationActiveEstablishment").removeClass("disabled")
                }
                requestUpdateEstablishmentActivePart()
            }
        }

        function cleanActivePaginationNumbers() {
            $("[name = 'paginationActiveEstablishment']" +
                "[id != 'toEndPaginationActiveEstablishment']" +
                "[id != 'toStartPaginationActiveEstablishment']").detach()
        }

        function updateActivePagination(totalPages, pageNumber) {
            cleanActivePaginationNumbers()
            if (totalPages > 1) {
                let toEndPaginationActiveEstablishment = $("#toEndPaginationActiveEstablishment")
                $("#paginationActiveEstablishment").removeClass('d-none')
                for (let i = 0; i < totalPages; i++) {
                    toEndPaginationActiveEstablishment.before(
                        $(
                            "<li class=page-item" +
                            "    id=activeEstablishmentPage" + i +
                            "    name=paginationActiveEstablishment" +
                            "    value=" + i +
                            ">" +
                            "   <a class=page-link>" + (i + 1) + "</a>" +
                            "</li>"
                        ).addClass((i === pageNumber ? "active" : ''))
                    )
                }
                toEndPaginationActiveEstablishment.val(totalPages - 1)
            } else {
                $("#paginationActiveEstablishment").addClass('d-none')
            }
        }

        function changeActiveEstablishmentsSizeTable() {
            $("#activePage").val($("#activeEstablishmentPage0").siblings($(".active")).val())
            requestUpdateEstablishmentActivePart()
        }

        function requestDeleteEstablishment(element) {
            $.ajax(
                {
                    url: "/establishment/delete/" + parseInt(element.id, 10),
                    method: "get",
                    data: $("#activeForm").serialize(),
                    success: function () {
                        requestUpdateEstablishmentActivePart()
                        requestUpdateEstablishmentDeletedPart()
                    }
                }
            )
        }

        /**
         *
         * Deleted part
         *
         */

        requestUpdateEstablishmentDeletedPart()

        function initializationDeletedEvents() {
            $("#selectCityEstablishmentsDeleted").change(() => requestUpdateEstablishmentDeletedPart())
            $("#deletedPageSize").change(
                function () {
                    changeDeletedEstablishmentsSizeTable(this)
                }
            )
            $("[name = 'paginationDeletedEstablishment']").click(
                function () {
                    clickDeletedEstablishmentsPagination(this)
                }
            )
            $("[name = 'recoverEstablishment']").click(
                function () {
                    requestRecoverEstablishment(this)
                }
            )
        }

        $('#selectCityEstablishmentsDeleted').select2({
            minimumInputLength: 1,
            ajax: {
                delay: 250,
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

        function requestUpdateEstablishmentDeletedPart() {
            $.ajax(
                {
                    url: "/establishment/deleted",
                    method: "get",
                    data: $("#deletedForm").serialize(),
                    success: function (establishments) {
                        updateEstablishmentsDeletedTable(establishments.content)
                        updateDeletedPagination(establishments.totalPages, establishments.pageable.pageNumber)
                        initializationDeletedEvents()
                        initializationCommonEvents()
                    }
                }
            )
        }

        function cleanDeletedEstablishmentTable() {
            $("#deletedTableBody").empty()
        }

        function updateEstablishmentsDeletedTable(establishments) {
            cleanDeletedEstablishmentTable()
            for (let establishment of establishments) {
                $("#deletedTableBody").append(
                    $(" <tr>" +
                        "<td>" + establishment.cityName + "</td>" +
                        "<td>" + establishment.streetName + "</td>" +
                        "<td>" + establishment.streetNumber + "</td>" +
                        "<td>" +
                        "     <button type='button'" +
                        "             id=" + establishment.idEstablishment + "editEstablishment" +
                        "             name='editEstablishment'" +
                        "             class='btn btn-info'" +
                        "      > " +
                        "     Редактировать" +
                        "     </button>" +
                        "     <button id=" + establishment.idEstablishment + "recoverEstablishment" +
                        "             name=recoverEstablishment" +
                        "             type=button " +
                        "             class='btn btn-primary'" +
                        "             >" +
                        "             Восстановить" +
                        "             </button>" +
                        "</td>" +
                        "</tr>")
                )
            }
        }

        function clickDeletedEstablishmentsPagination(element) {
            if (!$(element).hasClass("disabled") && !$(element).hasClass("active")) {
                cleanDeletedEstablishmentTable()
                $(element).siblings($(".deleted")).removeClass("active")
                $("#deletedEstablishmentPage" + element.value).addClass("active")
                $("#deletedPage").val(element.value)
                requestUpdateEstablishmentDeletedPart()
                if (element.value === $("#toStartPaginationDeletedEstablishment").val()) {
                    $("#toStartPaginationDeletedEstablishment").addClass("disabled")
                    $("#toEndPaginationDeletedEstablishment").removeClass("disabled")
                } else if (element.value === $("#toEndPaginationDeletedEstablishment").val()) {
                    $("#toEndPaginationDeletedEstablishment").addClass("disabled")
                    $("#toStartPaginationDeletedEstablishment").removeClass("disabled")
                } else {
                    $("#toEndPaginationDeletedEstablishment").removeClass("disabled")
                    $("#toStartPaginationDeletedEstablishment").removeClass("disabled")
                }
            }
        }

        function cleanDeletedPaginationNumbers() {
            $("[name = 'paginationDeletedEstablishment']" +
                "[id != 'toEndPaginationDeletedEstablishment']" +
                "[id != 'toStartPaginationDeletedEstablishment']").detach()
        }

        function updateDeletedPagination(totalPages, pageNumber) {
            cleanDeletedPaginationNumbers()
            if (totalPages > 1) {
                let toEndPaginationDeletedEstablishment = $("#toEndPaginationDeletedEstablishment")
                $("#paginationDeletedEstablishment").removeClass('d-none')
                for (let i = 0; i < totalPages; i++) {
                    toEndPaginationDeletedEstablishment.before(
                        $(
                            "<li class=page-item" +
                            "    id=deletedEstablishmentPage" + i +
                            "    name=paginationDeletedEstablishment" +
                            "    value=" + i +
                            ">" +
                            "   <a class=page-link>" + (i + 1) + "</a>" +
                            "</li>"
                        ).addClass((i === pageNumber ? "active" : ''))
                    )
                }
                toEndPaginationDeletedEstablishment.val(totalPages - 1)
            } else {
                $("#paginationDeletedEstablishment").addClass('d-none')
            }
        }

        function changeDeletedEstablishmentsSizeTable() {
            $("#deletedPage").val($("#deletedEstablishmentPage0").siblings($(".deleted")).val())
            requestUpdateEstablishmentDeletedPart()
        }

        function requestRecoverEstablishment(element) {
            $.ajax(
                {
                    url: "/establishment/recover/" + parseInt(element.id, 10),
                    method: "get",
                    data: $("#deletedForm").serialize(),
                    success: function () {
                        requestUpdateEstablishmentDeletedPart()
                        requestUpdateEstablishmentActivePart()
                    }
                }
            )
        }
    }
);

