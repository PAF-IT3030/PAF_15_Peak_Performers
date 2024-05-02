import { API_BASE_URL, ACCESS_TOKEN } from "../constants";

const request = (options) => {
  const headers = new Headers({
    "Content-Type": "application/json",
  });

  if (localStorage.getItem(ACCESS_TOKEN)) {
    headers.append(
      "Authorization",
      "Bearer " + localStorage.getItem(ACCESS_TOKEN)
    );
  }

  const defaults = { headers: headers };
  options = Object.assign({}, defaults, options);

  return fetch(options.url, options).then((response) =>
    response.json().then((json) => {
      if (!response.ok) {
        return Promise.reject(json);
      }
      return json;
    })
  );
};

export function getCurrentUser() {
  if (!localStorage.getItem(ACCESS_TOKEN)) {
    return Promise.reject("No access token set.");
  }

  return request({
    url: API_BASE_URL + "/api/test/all",
    method: "GET",
  });
}

export function login(loginRequest) {
  return request({
    url: API_BASE_URL + "/api/auth/signin",
    method: "POST",
    body: JSON.stringify(loginRequest),
  });
}

export function signup(signupRequest) {
  return request({
    url: API_BASE_URL + "/api/auth/signup",
    method: "POST",
    body: JSON.stringify(signupRequest),
  });
}

export function getAllWorkoutPlans() {
  return request({
    url: API_BASE_URL + "/api/workout/plan",
    method: "GET",
  });
}

export function craeteWorkoutPlans(plans) {
  return request({
    url: API_BASE_URL + "/api/workout/plan",
    method: "POST",
    body: JSON.stringify(plans),
  });
}

export function deleteWorkoutPlanById(planId) {
  return request({
    url: API_BASE_URL + "/api/workout/plan/" + planId,
    method: "DELETE",
  });
}

export function getAllMealPlans() {
  return request({
    url: API_BASE_URL + "/api/meal/plan",
    method: "GET",
  });
}

export function craeteMealPlans(plans) {
  return request({
    url: API_BASE_URL + "/api/meal/plan",
    method: "POST",
    body: JSON.stringify(plans),
  });
}

export function deleteMealPlanById(planId) {
  return request({
    url: API_BASE_URL + "/api/meal/plan/" + planId,
    method: "DELETE",
  });
}

export function uploadImage(file) {
  return request({
    url: API_BASE_URL + "/api/media/upload/image",
    method: "POST",
    body: JSON.stringify(file),
  });
}


// export function disLikePost({ likeId }) {
//   return request({
//     url: API_BASE_URL + "/posts/likes/" + likeId,
//     method: "DELETE"
//   });
// }

// export function getAllUsers() {
//   return request({
//     url: API_BASE_URL + "/user/all",
//     method: "GET"
//   });
// }

// export function postComment(comment) {
//   return request({
//     url: API_BASE_URL + "/api/comments/",
//     method: "POST",
//     body: JSON.stringify(comment)
//   });
// }

// export function likePost({ postId, userId }) {
//   return request({
//     url: API_BASE_URL + "/posts/" + postId + "/likes",
//     method: "POST",
//     body: JSON.stringify({ postId, userId })
//   });
// }

// export function sharePost(post) {
//   return request({
//     url: API_BASE_URL + "/api/v1/shared-posts",
//     method: "POST",
//     body: JSON.stringify(post)
//   });
// }

// export function getPostById(id) {
//   return request({
//     url: API_BASE_URL + "/api/v1/posts/" + id,
//     method: "GET"
//   });
// }

// export function deleteComment(id, userId) {
//   return request({
//     url: API_BASE_URL + "/api/comments/" + id + "/" + userId,
//     method: "DELETE"
//   });
// }

// export function editComment(id, comment) {
//   return request({
//     url: API_BASE_URL + "/api/comments/" + id,
//     method: "PUT",
//     body: JSON.stringify(comment)
//   });
// }

// export function deletePost(id) {
//   return request({
//     url: API_BASE_URL + "/api/v1/posts/" + id,
//     method: "DELETE"
//   });
// }

// export function deleteProfileById(id) {
//   return request({
//     url: API_BASE_URL + "/user/delete/" + id,
//     method: "DELETE"
//   });
// }

// export function updatePost(id, post) {
//   return request({
//     url: API_BASE_URL + "/api/v1/posts/" + id,
//     method: "PUT",
//     body: JSON.stringify(post)
//   });
// }

// export function getSharedPosts() {
//   return request({
//     url: API_BASE_URL + "/api/v1/shared-posts",
//     method: "GET"
//   });
// }

// export function updateProfile(id, user) {
//   return request({
//     url: API_BASE_URL + "/user/edit/" + id,
//     method: "PUT",
//     body: JSON.stringify(user)
//   });
// }

// export function deleteSharedPost(id) {
//   return request({
//     url: API_BASE_URL + "/api/v1/shared-posts/" + id,
//     method: "DELETE"
//   });
// }

// export function updateSharedPost(id, post) {
//   return request({
//     url: API_BASE_URL + "/api/v1/shared-posts/" + id,
//     method: "PUT",
//     body: JSON.stringify(post)
//   });
// }
