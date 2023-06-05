export default class ResultJSON {
  responseCode: Number
  responseTitle: string
  responseText: string
  responseObject: object

  constructor(
    responseCode: Number,
    responseTitle: string,
    responseText: string,
    responseObject: object
  ) {
    this.responseCode = responseCode
    this.responseTitle = responseTitle
    this.responseText = responseText
    this.responseObject = responseObject
  }
}
