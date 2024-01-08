import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'package:flutter/material.dart';
import 'e_c_g_popup_results_model.dart';
export 'e_c_g_popup_results_model.dart';

class ECGPopupResultsWidget extends StatefulWidget {
  const ECGPopupResultsWidget({super.key});

  @override
  _ECGPopupResultsWidgetState createState() => _ECGPopupResultsWidgetState();
}

class _ECGPopupResultsWidgetState extends State<ECGPopupResultsWidget> {
  late ECGPopupResultsModel _model;

  @override
  void setState(VoidCallback callback) {
    super.setState(callback);
    _model.onUpdate();
  }

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => ECGPopupResultsModel());
  }

  @override
  void dispose() {
    _model.maybeDispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: 100.0,
      decoration: BoxDecoration(
        color: FlutterFlowTheme.of(context).secondaryBackground,
      ),
      child: Column(
        mainAxisSize: MainAxisSize.max,
        children: [
          Text(
            'ECG Reading Result',
            style: FlutterFlowTheme.of(context).bodyMedium.override(
                  fontFamily: 'Readex Pro',
                  fontSize: 40.0,
                  fontWeight: FontWeight.w500,
                ),
          ),
          Text(
            'Below are the readings of the ECG scan. Please confirm the quality of the scan.',
            style: FlutterFlowTheme.of(context).bodyMedium.override(
                  fontFamily: 'Readex Pro',
                  fontSize: 28.0,
                ),
          ),
          ClipRRect(
            borderRadius: BorderRadius.circular(8.0),
            child: Image.network(
              'https://picsum.photos/seed/945/600',
              width: 300.0,
              height: 200.0,
              fit: BoxFit.cover,
            ),
          ),
        ],
      ),
    );
  }
}
