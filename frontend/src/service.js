export function get(token, name, successCallback, failureCallback) {
  const headers = authorization(token);
  fetch(address(name), {
    method: "GET",
    headers
  })
    .then(is200)
    .then(json)
    .then(successCallback)
    .catch(failureCallback);
}

function authorization(token) {
  const headers = {};
  if (token) {
    headers.Authorization = "Bearer " + token;
  }
  return headers;
}

function address(name) {
  return "/backend/resources/" + name;
}

function is200(response) {
  var status = response.status;
  if (200 <= status && status < 300) {
    return response;
  } else {
    var error = new Error(response.statusText);
    error.response = response;
    throw error;
  }
}

function json(response) {
  return response.json();
}

export function save(token, name, successCallback, failureCallback, entity) {
  const headers = authorization(token);
  headers["Content-Type"] = "application/json";
  fetch(address(name), {
    method: "POST",
    headers,
    body: JSON.stringify(entity)
  })
    .then(is200)
    .then(json)
    .then(successCallback)
    .catch(failureCallback);
}

export function remove(token, name, successCallback, failureCallback, id) {
  const headers = authorization(token);
  fetch(address(name) + "/" + id, {
    method: "DELETE",
    headers
  })
    .then(is200)
    .then(successCallback)
    .catch(failureCallback);
}
