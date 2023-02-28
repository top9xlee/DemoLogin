<%@page pageEncoding="UTF-8" %><%--// đọc chữ tiếng việt--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col-md-6 list-absence" style="margin-top: 10px;margin-bottom: 10px;margin-left: 100px">
        <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
            <div class="m-portlet m-portlet--mobile "style="margin: auto;width: 100%">
                <div class="m-portlet__head">
                    <div class="m-portlet__head-caption" style="margin: auto;">
                        <h2 class="m-portlet__head-text" style="color:black;font-size:2.2rem">
                            Danh sách đơn nghỉ
                        </h2>
                    </div>
                </div>
                <div class="m-portlet__body"style="height: 250px;">
                    <div class = "row">
                        <div class="col-md-9">
                            <div class="form-group m-form__group row">
                                <div class="text-center col-md-12">
                                    <h4 class="m-form__section" style="color:#0C0C0C;">${department.departmentName}</h4>
                                </div>
                            </div>
                            <div class="form-group m-form__group row">
                                <label  class="col-4 col-form-label1">Tên trưởng phòng</label>
                                <div class="col-7">
                                    <input disabled class="form-control m-input" type="text" value="${department.headName}" }>
                                </div>
                            </div>
                            <div class="form-group m-form__group row">
                                <label  class="col-4 col-form-label1">Số nhân viên</label>
                                <div class="col-7">
                                    <input disabled class="form-control m-input" type="text" value="${department.numberOfUser}">
                                </div>
                            </div>
                            <%--            <div class="form-group m-form__group row">--%>
                            <%--                <label  class="col-4 col-form-label">Full Name</label>--%>

                            <%--            </div>--%>
                            <%--            <div class="form-group m-form__group row">--%>
                            <%--                <label  class="col-4 col-form-label">Full Name</label>--%>
                            <%--                <div class="col-8">--%>
                            <%--                    <input disabled class="form-control m-input" type="text" value="Mark Andre">--%>
                            <%--                </div>--%>
                            <%--            </div>--%>
                        </div>
                        <div class="col-md-3">
                            <!--begin: Search Form -->
                            <div><button type="button" class="btn btn-outline-primary" style="width: 130px; margin: 2px"><a href = "/list-posted/${department.departmentId}" style="color: #0f0f11">Tất cả đơn </a></button></div>
                            <div><button type="button" class="btn btn-outline-primary" style="width: 130px; margin: 2px"><a href = "/list-nonenable/${department.departmentId}" style="color: #0f0f11">Đơn chưa duyệt </a></button></div>
                            <div><button type="button" class="btn btn-outline-primary" style="width: 130px; margin: 2px"><a href =  "/list-enabled/${department.departmentId}"style="color: #0f0f11">Đã duyệt</a></button></div>
                            <div><button type="button" class="btn  btn-outline-primary" style="width: 130px; margin: 2px"><a href ="/list-rejected/${department.departmentId}"style="color: #0f0f11">Từ chối </a></button></div>
                        </div>
                        <!--end: Search Form -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-5" style="margin-top: 10px;margin-bottom: 10px">

<%--        <div>--%>
<%--            <iframe src="https://calendar.google.com/calendar/embed?src=lut19mefheusa4ioesapbtog0k%40group.calendar.google.com&ctz=Asia%2FHo_Chi_Minh" style="border: 0" width="650" height="310" frameborder="0" scrolling="no"></iframe>--%>
<%--        </div>--%>

    </div>
    <div class="col-md-12" >
        <div style="background-color: white;margin: auto;width: 200px">
        <c:choose>
            <c:when test="${param.get('status').equals('success')}">
                <p class="message-register" style="color: rgb(22, 161, 255); font-size: 16px;text-align: center;font-size: 1.4rem;">Duyệt thành công.</p>
            </c:when>
            <c:when test="${param.get('error').equals('fail')}">
                <p class="message-register" style="color: rgb(20, 157, 252); font-size: 16px;text-align: center;font-size: 1.4rem;">Từ chối thành công.</p>
            </c:when>
        </c:choose>
        </div>
    </div>
</div>

<div class="col-md-12" style="margin-top: 30px;margin: auto">
    <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
        <div class="m-portlet m-portlet--mobile "style="margin: auto;width: 90%">
            <div class="m-portlet__body">
        <!--begin: Datatable -->
                <table width="100%"class="table table-striped- table-bordered table-hover table-checkable dataTable no-footer table" style="overflow: scroll" >
            <thead>
            <tr>
                <th title="Field #1" data-field="Mã đơn">Order ID</th>
                <th title="Field #2" data-field="Owner">Tên nhân viên</th>
                <th title="Field #3" data-field="Type">Lý do</th>
                <th title="Field #4" data-field="Contact">Nghỉ từ ngày</th>
                <th title="Field #5" data-field="CarMake">Đến ngày</th>
                <th title="Field #6" data-field="CarModel">Số ngày</th>
                <th title="Field #7" data-field="Color">Ngày gửi đơn</th>
                <th title="Field #8" data-field="DepositPaid">Tình trạng đơn</th>
                <th title="Field #9" data-field="OrderDate">Duyệt</th>
                <th title="Field #10" data-field="Status">Từ chối</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="absens" items="${absens}">
                <fmt:formatDate var="startDate" value="${absens.startDate}" pattern="yyyy-MM-dd" />
                <fmt:formatDate var="endDate" value="${absens.endDate}" pattern="yyyy-MM-dd" />
                <tr>
                    <td >${absens.postId}</td>
                    <td >${absens.userName}</td>
                    <td >${absens.description}</td>
                    <td ><time><fmt:formatDate value="${absens.startDate}" pattern="dd-MM-yyyy" /></time> </td>
                    <td ><time><fmt:formatDate value="${absens.endDate}" pattern="dd-MM-yyyy " /></time></td>
                    <td >${absens.dayOff}</td>
                    <td ><time><fmt:formatDate value="${absens.createDate}" pattern="dd-MM-yyyy " /></time></td>
                    <td >  <c:if test="${absens.enablePost ==0}">
                        <span class=" m-badge  m-badge--danger m-badge--wide" style="margin: auto">${absens.statusPost}</span>
                    </c:if>
                        <c:if test="${absens.enablePost ==1}">
                            <span class=" m-badge  m-badge--info m-badge--wide"style="margin: auto">${absens.statusPost}</span>
                        </c:if>
                        <c:if test="${absens.enablePost ==2}">
                            <span class=" m-badge  m-badge--brand m-badge--wide"style="margin: auto">${absens.statusPost}</span>
                        </c:if></td>
                    <td ><a href="/enable-absence/${absens.postId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Phê duyệt"
                            onclick="return confirm('Bạn có muốn duyệt đơn này không?');">
                        <i class="la la-check"></i>
                    </a></td>
                    <td>
<%--                        <a href="/delete-absence/${absens.postId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Từ chối"--%>
<%--                            onclick="return confirm('Bạn có muốn từ chối đơn này không?');">--%>
<%--                        <i class="la la-remove"></i>--%>
<%--                    </a>--%>
<%--                        <a class="dialog-btn" href="#reject-dialog"  title="Từ chối">--%>
<%--                            <i class="la la-remove"></i>--%>
<%--                            <div class="dialog overlay" id = "reject-dialog">--%>
<%--                                <a href ="#" class="over-lay-close"></a>--%>
<%--                                <div class="dialog-body">--%>
<%--                                    <a class="dialog-close-btn" href = "#">&times;</a>--%>
<%--                                    <h1> hello</h1>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </a>--%>
    <a class = "m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill">
    <i type="button" class=" la la-remove " data-toggle="modal" data-target="#exampleModal"></i>
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Hãy nêu lí do</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form method="post" enctype="multipart/form-data" action="/note">
                <div class="modal-body">
                        <input hidden value="${absens.postId}" name="postId">
                        <div class="form-group">
                            <label for="message-text" class="col-form-label-modal">Lí do:</label>
                            <textarea class="form-control" id="message-text" name="description"></textarea>
                        </div>
                </div>
                <div class="modal-footer">
                    <button type="reset" class="btn btn-secondary">Reset</button>
                    <button type="submit" class="btn btn-primary">Send message</button>
                </div>
                </form>
            </div>
        </div>
    </div>
    </a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
            </div>
        </div>
    </div>
</div>
</div>

